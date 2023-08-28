// Steps/Contact.js

import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { useAppState } from "./state";
import {Button, Input, Form, Field} from "./Forms";
import Sheet from '@mui/joy/Sheet';
import { CssVarsProvider } from "@mui/joy";

export const Register= () => {
  const [state, setState] = useAppState();
  const {
    handleSubmit,
    register,
    watch,
    formState: { errors },
  } = useForm({ defaultValues: state.customer, mode: "onSubmit" });
  const watchPassword = watch("password");
  const navigate = useNavigate();

  const saveData = ({emailId, password}) => {
    // console.log(state);
    setState({...state, customer:{emailId, password}});
    // console.log(state);
    navigate("/personal-details");
  };

  return (
    <div>
        <CssVarsProvider>
    <Sheet
          sx={{
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
          variant="outlined"
        >
    <Form onSubmit={handleSubmit(saveData)}>
      <fieldset>
        <legend><b>Account Details</b></legend>
        
        <Field label="Email" error={errors?.emailId}>
          <Input
            {...register("emailId", { required: "Email is required", 
                                    pattern: {
                                        value : /^[^@ ]+@[^@]+\.[^@.]{2,}$/ ,
                                        message: "Please enter valid email"
                                    }})}
            type="email"
            id="emailId"
          />
        </Field>
        <Field label="Password" error={errors?.password}>
          <Input
            {...register("password", { required: "Password is required", pattern: {
                value: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$/,
                message: "Please check all the conditions of password"
            } })}
            type="password"
            id="password"
          />
        </Field>
          <p style={{fontSize: "10px"}}>Password should be of 6-15 characters containing capital letter and small letter </p>
        <Field label="Confirm password" error={errors?.confirmPassword}>
          <Input
            {...register("confirmPassword", {
              required: "Confirm the password",
              validate: (value) =>
                value === watchPassword || "The passwords do not match",
            })}
            type="password"
            id="password-confirm"
          />
        </Field>
        <Button>Next {">"}</Button>
      </fieldset>
    </Form>
    </Sheet>
    </CssVarsProvider>
    </div>
  );
};
