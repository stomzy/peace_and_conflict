import React from "react";
import { PieChart, Pie, Cell, Legend } from "recharts";

const data = [
  { name: "Open", value: 20 },
  { name: "Resolved", value: 45 },
  { name: "In Progress", value: 15 },
];

const COLORS = ["#F59E0B", "#10B981", "#3B82F6"];

const CaseStatusChart: React.FC = () => (
  <div className="bg-white p-4 rounded shadow w-full md:w-1/2">
    <h3 className="text-lg font-semibold mb-2">Case Status</h3>
    <PieChart width={300} height={250}>
      <Pie data={data} dataKey="value" cx={140} cy={100} outerRadius={80} label>
        {data.map((entry, i) => (
          <Cell key={`cell-${i}`} fill={COLORS[i % COLORS.length]} />
        ))}
      </Pie>
      <Legend />
    </PieChart>
  </div>
);

export default CaseStatusChart;
