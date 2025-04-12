import React from "react";
import DashboardLayout from "../components/DashboardLayout";
import StatCard from "../components/widgets/StatCard";
import CaseStatusChart from "../components/widgets/CaseStatusChart";
import RecentCases from "../components/widgets/RecentCases";

const Dashboard: React.FC = () => {
  return (
    <DashboardLayout>
      <h1 className="text-2xl font-bold mb-4">Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
        <StatCard title="Total Cases" value={80} />
        <StatCard title="Open Cases" value={20} />
        <StatCard title="Resolved Cases" value={45} />
      </div>

      <div className="flex flex-col md:flex-row gap-4">
        <CaseStatusChart />
        {/* Placeholder for future map/chart/etc */}
        <div className="bg-white p-4 rounded shadow w-full md:w-1/2">
          <h3 className="text-lg font-semibold mb-2">Coming Soon</h3>
          <p>More insights coming here...</p>
        </div>
      </div>

      <RecentCases />
    </DashboardLayout>
  );
};

export default Dashboard;
