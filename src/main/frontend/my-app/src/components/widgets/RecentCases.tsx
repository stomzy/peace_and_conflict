import React from "react";

const dummyCases = [
  { id: 1, title: "Conflict in Area A", status: "Open", date: "2025-04-10" },
  {
    id: 2,
    title: "Mediation in Zone B",
    status: "Resolved",
    date: "2025-04-09",
  },
  {
    id: 3,
    title: "Dispute in Region C",
    status: "In Progress",
    date: "2025-04-08",
  },
];

const RecentCases: React.FC = () => (
  <div className="bg-white p-4 rounded shadow mt-6">
    <h3 className="text-lg font-semibold mb-2">Recent Cases</h3>
    <table className="w-full text-left border-collapse">
      <thead>
        <tr className="text-sm text-gray-600 border-b">
          <th className="py-2">Case</th>
          <th className="py-2">Status</th>
          <th className="py-2">Date</th>
        </tr>
      </thead>
      <tbody>
        {dummyCases.map((c) => (
          <tr key={c.id} className="border-b text-sm">
            <td className="py-2">{c.title}</td>
            <td className="py-2">{c.status}</td>
            <td className="py-2">{c.date}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

export default RecentCases;
