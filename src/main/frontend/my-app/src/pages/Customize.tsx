import React from "react";
import DashboardLayout from "../components/DashboardLayout";
import BrandingSettings from "../components/BrandingSettings";

const Dashboard: React.FC = () => {
  return (
    <DashboardLayout>
      <BrandingSettings />
    </DashboardLayout>
  );
};

export default Dashboard;
