"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const axios_1 = __importDefault(require("axios"));
const kafkajs_1 = require("kafkajs");
const api_1 = __importDefault(require("@opentelemetry/api"));
const tracer = api_1.default.trace.getTracer('my-service-tracer');
const kafkaBrokers = process.env.KAFKA_BOOTSTRAP_SERVERS || 'localhost:29092';
const kafkaConfig = { brokers: [kafkaBrokers] };
const kafka = new kafkajs_1.Kafka(kafkaConfig);
const consumer = kafka.consumer({ groupId: 'service-a' });
consumer.connect();
consumer.subscribe({ topic: 'topic-d', fromBeginning: true });
consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
        var _a;
        const msg = message.value ? message.value.toString() : '';
        tracer.startActiveSpan('Extrayendo info del mensaje', span => {
            span.setAttribute('Mensaje', msg);
            // Be sure to end the span!
            span.end();
        });
        console.log({
            value: (_a = message.value) === null || _a === void 0 ? void 0 : _a.toString(),
        });
    },
});
const PORT = parseInt(process.env.PORT || "8090");
const index = (0, express_1.default)();
index.use(express_1.default.json());
index.use(express_1.default.urlencoded({ extended: true }));
index.post("/", (req, res) => {
    const processId = Date.now();
    const initialValue = req.body.initialValue;
    callServiceB(processId, initialValue).then(r => res.send({ 'initialValue': initialValue }));
});
index.listen(PORT, () => {
    console.log(`Listening for requests on http://localhost:${PORT}`);
});
async function callServiceB(processId, initialValue) {
    try {
        console.log(`Calling service b`);
        const response = await axios_1.default.post('http://localhost:8081/b-service', {
            "id": processId,
            "initialValue": initialValue,
            "currentValue": initialValue
        });
        console.log(`Response received ${response.status}`);
    }
    catch (error) {
        console.error(error);
    }
}
