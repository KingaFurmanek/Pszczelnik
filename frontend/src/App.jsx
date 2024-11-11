import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import HiveFormStep1 from "./pages/HiveFormStep1.jsx";
import HiveFormStep2 from "./pages/HiveFormStep2.jsx";
import Hives from "./pages/Hives.jsx";
import Login from "./pages/Login.jsx";
import SignUp from "./pages/SignUp.jsx";
import HiveCategories from "./pages/HiveCategories.jsx";
import Tasks from "./components/HiveInspections/Tasks.jsx";
import Forum from "./components/Forum/Forum.jsx";
import PostComments from "./components/Forum/PostComments.jsx";
import Profile from "./pages/Profile.jsx";
import Health from "./components/HiveInspections/Health/Health.jsx";
import HealthRecords from "./components/HiveInspections/Health/HealthRecords.jsx";
import { AuthContext } from './AuthContext.jsx';
import {useContext} from "react";

function App() {
    const { userRole } = useContext(AuthContext);

    return (
        <Router>
            <Routes>
                {(userRole && userRole.includes("admin")) ? (
                    <>
                        <Route path="/forum" element={<Forum />} />
                        <Route path="/profile" element={<Profile />} />
                    </>
                ) : (
                    <>
                    <Route path="/login" element={<Login />} />
                    <Route path="/signUp" element={<SignUp />} />
                    <Route path="/addHiveStep1" element={<HiveFormStep1 />} />
                    <Route path="/addHiveStep2" element={<HiveFormStep2 />} />
                    <Route path="/hives" element={<Hives />} />
                    <Route path="/hive/:hiveId" element={<HiveCategories />} />
                    <Route path="/hive/:hiveId/tasks" element={<Tasks />} />
                    <Route path="/forum" element={<Forum />} />
                    <Route path="/forum/post/:postId" element={<PostComments />} />
                    <Route path="/profile" element={<Profile />} />
                    <Route path="/hive/:hiveId/health" element={<Health />} />
                    <Route path="/hive/:hiveId/healthRecords" element={<HealthRecords />} />
                    </>
                )}
            </Routes>
        </Router>
    );
}

export default App;
