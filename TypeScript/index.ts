import express from "express";
import bodyParser from "body-parser";
import cors from "cors";
import { getAccounts, deposit, withdraw } from "./banking";

const app = express();
app.use(cors());
app.use(bodyParser.json());

app.get("/accounts", (req, res) => {
    res.json(getAccounts());
});

app.post("/deposit", (req, res) => {
    const { id, amount } = req.body;
    const acc = deposit(id, amount);
    acc ? res.json(acc) : res.status(404).send("Account not found");
});

app.post("/withdraw", (req, res) => {
    const { id, amount } = req.body;
    const acc = withdraw(id, amount);
    acc ? res.json(acc) : res.status(400).send("Insufficient funds");
});

const PORT = 4000;
app.listen(PORT, () => {
    console.log(`Banking app running at http://localhost:${PORT}`);
});
