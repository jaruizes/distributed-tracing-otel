import express, { Express } from "express";
import axios from 'axios';
import { Kafka, KafkaConfig, Consumer, EachMessagePayload } from 'kafkajs';
import opentelemetry from "@opentelemetry/api";
const tracer = opentelemetry.trace.getTracer(
    'my-service-tracer'
);

const kafkaBrokers = process.env.KAFKA_BOOTSTRAP_SERVERS || 'localhost:29092';
const serviceBURL = process.env.SERVICEB_URL || 'http://localhost:8081';

const kafkaConfig: KafkaConfig = { brokers: [ kafkaBrokers ], connectionTimeout: 10000, requestTimeout: 60000, retry: { initialRetryTime: 45000, retries: 20} }
const kafka = new Kafka(kafkaConfig)

const consumer = kafka.consumer({ groupId: 'service-a' })


consumer.connect()
consumer.subscribe({ topic: 'topic-d', fromBeginning: true })
consumer.run({
    eachMessage: async ({ topic, partition, message }: EachMessagePayload) => {
        const msg = message.value ? message.value.toString() : '';
        tracer.startActiveSpan('Extrayendo info del mensaje', span => {
            span.setAttribute('Mensaje', msg)

            // Be sure to end the span!
            span.end();
        });
        console.log({
            value: message.value?.toString(),
        })
    },
})

const PORT: number = parseInt(process.env.PORT || "8080");
const index: Express = express();

index.use(express.json());
index.use(express.urlencoded({ extended: true }));

index.post("/", (req, res) => {
    const processId = Date.now();
    const initialValue = req.body.initialValue
    callServiceB(processId, initialValue).then(r => res.send({'initialValue': initialValue}));

});

index.listen(PORT, () => {
    console.log(`Listening for requests on http://localhost:${PORT}`);
});


async function callServiceB(processId: number, initialValue: number) {
    try {
        console.log(`Calling service b`);
        const response = await axios.post(`${serviceBURL}/b-service`, {
            "id": processId,
            "initialValue": initialValue,
            "currentValue": initialValue
        });
        console.log(`Response received ${response.status}`);
    } catch (error) {
        console.error(error);
    }
}
