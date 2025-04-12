import React from "react";

interface StatCardProps {
  title: string;
  value: string | number;
  bg?: string;
}

const StatCard: React.FC<StatCardProps> = ({
  title,
  value,
  bg = "bg-white",
}) => (
  <div className={`p-4 rounded shadow ${bg}`}>
    <h3 className="text-gray-600 text-sm">{title}</h3>
    <p className="text-2xl font-semibold">{value}</p>
  </div>
);

export default StatCard;
