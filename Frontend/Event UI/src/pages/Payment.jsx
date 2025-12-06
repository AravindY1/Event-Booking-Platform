import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/apiClient";

export default function Payment() {
  const { bookingId } = useParams();
  const [amount, setAmount] = useState("");
  const [resp, setResp] = useState(null);
  const [err, setErr] = useState("");

  useEffect(() => {
    // optionally you can fetch booking details to populate a default amount
  }, [bookingId]);

  const doPayment = async () => {
    setErr("");
    try {
      const res = await api.post("http://localhost:8083/payment/process", {
        bookingId: Number(bookingId),
        amount: Number(amount)
      });
      setResp(res.data);
    } catch (e) {
      setErr("Payment failed");
    }
  };

  return (
    <div className="max-w-md bg-white p-6 rounded shadow">
      <h2 className="text-2xl font-semibold mb-4">Payment for booking #{bookingId}</h2>
      {err && <div className="text-red-600 mb-3">{err}</div>}
      {resp ? (
        resp.status === "SUCCESS" ? (
          <div className="p-4 bg-green-50 text-green-700 rounded">
            <strong>Payment Successful</strong>
            <div>Transaction: {resp.transactionRef}</div>
          </div>
        ) : (
          <div className="p-4 bg-red-50 text-red-700 rounded">Payment Failed</div>
        )
      ) : (
        <>
          <input placeholder="Amount" value={amount} onChange={e => setAmount(e.target.value)} className="w-full p-3 border rounded mb-3" />
          <button onClick={doPayment} className="w-full py-3 bg-blue-600 text-white rounded">Pay</button>
        </>
      )}
    </div>
  );
}
