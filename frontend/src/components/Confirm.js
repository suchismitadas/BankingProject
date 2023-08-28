// Steps/Confirm.js
import { useState } from "react";
import { useForm } from "react-hook-form";
import { useAppState } from "./state";
import { Button, Form, Section, SectionRow } from "./Forms";
import { Sheet, CssVarsProvider } from "@mui/joy";
import axios from "axios";
import { Link } from "react-router-dom";
export const Confirm = () => {
  const [state] = useAppState();
  // console.log(state);
  const { handleSubmit } = useForm({ defaultValues: state });
const [registered, setRegistered] = useState(false);
const [error, setError] = useState({});
const [hasError, setHasError] = useState(false);
const [id, setId] = useState(null);
  const submitData = async (data) => {
    console.log(data);
    console.log(JSON.stringify(data));

    try {
      const res = await axios.post("http://localhost:8080/register", data);
      console.log(res);
      console.log(res.data);
      setRegistered(true);
      setId(res.data.id);
    } catch(error) {
      setHasError(true);
      setError(error.response.data);
      console.log(error.response.data);
    }
  };

  return (
    <>
    <CssVarsProvider>
      <Sheet sx={{
            width: 600,
            mx: 'auto', // margin left & right
            my: 4, // margin top & bottom
            py: 3, // padding top & bottom
            px: 2, // padding left & right
            display: 'flex',
            flexDirection: 'column',
            gap: 2,
            borderRadius: 'sm',
            boxShadow: 'md',
          }}
          variant="outlined">
    <Form onSubmit={handleSubmit(submitData)}>
      <h1 className="mb-4">Confirm</h1>
      <Section title="Account info" url="/register-new">
        <SectionRow>
          <div>Email</div>
          <div>{state.customer.emailId}</div>
        </SectionRow>
      </Section>
      <Section title="Personal info" url="/personal-details">
      <SectionRow>
          <div>First name</div>
          <div>{state.customerDetails.firstName}</div>
        </SectionRow>
        <SectionRow>
          <div>Last name</div>
          <div>{state.customerDetails.lastName}</div>
        </SectionRow>
        <SectionRow>
          <div>Mobile number</div>
          <div>{state.customerDetails.mobileNumber}</div>
        </SectionRow>
        <SectionRow>
          <div>Date of Birth</div>
          <div>{state.customerDetails.dob}</div>
        </SectionRow>
        <SectionRow>
          <div>Address</div>
          <div>{state.customerDetails.permanentAddress}</div>
        </SectionRow>
        <SectionRow>
          <div>Aadhar number</div>
          <div>{state.customerDetails.aadhar}</div>
        </SectionRow>
        <SectionRow>
          <div>PAN</div>
          <div>{state.customerDetails.pan}</div>
        </SectionRow>
        <SectionRow>
          <div>Occupation</div>
          <div>{state.customerDetails.occupation}</div>
        </SectionRow>
      </Section>
      <div className="d-flex justify-content-start">
        <Button>Submit</Button>
      </div>
      {registered && <p>Success! Your customer id is {id}. Use this for <Link to="/login"> login</Link>  to internet banking.</p>}
      {hasError && <div>
        <p style={{textAlign: 'center'}}> <strong>{error.property}</strong>: {error.code}. <br></br>
        <strong>Description: </strong>{error.message}</p>
        </div>}
    </Form>
    </Sheet>
    </CssVarsProvider>
    </>
  );
};
