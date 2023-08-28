import React from 'react';
import { BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import Home from './Home';
import Dashlist from './components/dashlist';
import Welcome from './components/welcome';
import AccountSummary from "./AccountSummary";
import AccountTransactions from "./AccountTransactions"
import UserLogin from './components/Userlogin'
// import Register2 from './components/register2';
import Profile from './profile';
import { Register} from './components/register';
import { PersonalDetails } from './components/PersonalDetails';
import { Confirm } from './components/Confirm';
import { AppProvider } from './components/state';
import History from './components/History';
import AdminLogin from './components/Adminlogin';
import Admin from './components/admin';
import Withdraw from './components/Withdraw'
function App() {
  // const { register,handleSubmit, formState : {errors} } = useForm();
  //   const onSubmit = (e) =>{
  //       alert("Data Submitted")
  //   }
  return (
    < div >
    <Dashlist/>
    
    <AppProvider>
      <Router>
      <Routes>
      <Route path="/" element={<Home/>} />
      <Route path="/login" element={<UserLogin/>} />
      <Route path="/register" element={<Register/>} />
      {/* <Route path="/register2" element={<Register2/>} /> */}
      <Route path='/login/welcome' element={<Welcome/>}/>
      <Route path="/withdraw" element ={<Withdraw/>}></Route>
      {/* <Route element={<Error/>}/> */}
      <Route path="/accountsummary" element={<AccountSummary/>} />
      <Route path="/accounttransactions" element={<AccountTransactions/>} />
      <Route path="/profile" element={<Profile/>}/>
          {/* <Route path="/register-new" element={<RegisterNew />} /> */}
          <Route path="/personal-details" element={<PersonalDetails />} />
          <Route path="/confirm" element={<Confirm />} />
          <Route path="/history" element={<History/>}/>
          <Route path="/adminlogin" element={<AdminLogin/>}/>
          <Route path="/login/admin" element={<Admin/>}/>
      </Routes>
      </Router>
    </AppProvider>

  
    </div>
  );
}

export default App;
