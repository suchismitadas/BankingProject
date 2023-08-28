// Steps/About.js

import { useForm } from "react-hook-form";
import { useNavigate, Link } from "react-router-dom";
import { useAppState } from "./state";
import { Button, Field, Form, Input } from "./Forms";
import { Sheet, CssVarsProvider } from "@mui/joy";

export const PersonalDetails = () => {
  const [state, setState] = useAppState();
  // console.log(state);
  const { handleSubmit, register, formState: {errors}} = useForm({ defaultValues: state.customerDetails });
  const navigate = useNavigate();

  const saveData = (data) => {
    setState({...state, customerDetails: data});
    navigate("/confirm");
  };

  function getAge(dateString) {
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

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
        
    <Form onSubmit={handleSubmit(saveData)}>
      <fieldset>
        <legend><b>Personal Details</b></legend>
        <Field label="First name" error={errors?.firstName}>
          <Input
            {...register("firstName", { required: "First name is required" })}
            id="first-name"
          />
        </Field>
        <Field label="Last name" error={errors?.lastName}>
          <Input
            {...register("lastName", { required: "Last name is required" })}
            id="last-name"
          />
        </Field>
        <Field label="Date of birth" error={errors?.dob}>
          <Input
            {...register("dob", { required: "Date of birth is required",
          validate: value => getAge(value) > 17 || 'Age should be above 18' })}
            id="dob"
            type="date"
          />
          </Field>
          <Field label="Aadhar Number" error={errors?.aadhar}>
          <Input
            {...register("aadhar", { required: "16 digit Aadhar number is required", 
            minLength: {
              value: 12, message: "Aadhar should be of 12 digits"}, 
              maxLength: { value: 16, message: "Aadhar should be of 12 digits"}} )}
            id="aadhar"
            type="number"
          />
        </Field>
        <Field label="PAN" error={errors?.pan}>
          <Input
            {...register("pan", { required: "Pan number is required", minLength: { value: 10, message: "PAN should be of 10 digits"},
                        maxLength: { value: 10, message: "PAN should be of 10 digits"}})}
            id="pan"
          />
        </Field>
        <Field label="Address" error={errors?.permanentAddress}>
          <Input
            {...register("permanentAddress", { required: "Permanent address is required" })}
            id="address"
          />
        </Field>
        <Field label="Mobile Number" error={errors?.mobileNumber}>
          <Input
            {...register("mobileNumber", { required: "Mobile number is required",
                minLength: { value: 10, message: "Mobile number should be of 10 digits"},
            maxLength: { value: 10, message: "Mobile number should be of 10 digits"} 
          })}
            id="mobile"
            type="tel"
          />
        </Field>
        <Field label="Occupation" error={errors?.occupation}>
          <Input
            {...register("occupation", { required: "Occupation is required" })}
            id="occupation"
          />
        </Field>
        <div className="button-row">
          <Link className={`btn btn-secondary`} to="/register-new">
            {"<"} Previous
          </Link>
          <Button>Next {">"}</Button>
        </div>
      </fieldset>
    </Form>
    </Sheet>
      </CssVarsProvider>
      </>
  );
};
