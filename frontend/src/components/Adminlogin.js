import * as React from 'react';
import { CssVarsProvider, useColorScheme } from '@mui/joy/styles';
import Sheet from '@mui/joy/Sheet';
import Typography from '@mui/joy/Typography';
// import FormControl from '@mui/joy/FormControl';
// import FormLabel from '@mui/joy/FormLabel';
// import Input from '@mui/joy/Input';
// import Button from '@mui/joy/Button';
import Link from '@mui/joy/Link';
import { useForm} from "react-hook-form";
import { useState } from "react";
import { Navigate } from "react-router-dom";
import axios from 'axios';
import {Button, Field, Form, Input} from "./Forms";

function ModeToggle() {
  const { mode, setMode } = useColorScheme();
  const [mounted, setMounted] = React.useState(false);

  // necessary for server-side rendering
  // because mode is undefined on the server
 
 

  React.useEffect(() => {
    setMounted(true);
  }, []);
  if (!mounted) {
    return null;
  }

  return (
    <Button
      variant="primary"
      onClick={() => {
        setMode(mode === 'light' ? 'dark' : 'light');
      }}
    >
      {mode === 'light' ? 'Turn dark' : 'Turn light'}
    </Button>
  );
}

export default function AdminLogin() {

    const {register,handleSubmit ,formState:{errors}} = useForm({defaultValues: {id: 1, password:123456}});
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [hasError, setHasError] = useState(false);
    const [errorDetail, setErrorDetail] = useState({});
    const doLogin = async (data) =>{
      
        console.log(data);
        try {
            const res = await axios.post("http://localhost:8080/login", data);
            console.log(res);
            const tokenGenerated = res.data.message;
            localStorage.setItem('token', tokenGenerated);
            localStorage.setItem('id', data.id);
            setIsSubmitted(true);
        }
        catch(error) {
            setHasError(true);
            setErrorDetail(error.message);

        }
        // it will output the data on the consol
       
    };
    if (isSubmitted)
         {
            console.log(isSubmitted);
               return <Navigate to="/login/admin"/>;
                
         }
        else if(hasError){
            return (
                <>
            <p> i am error</p>
            <p>{errorDetail}</p>
            </>
            )
        }
  return (
    
    <CssVarsProvider>
      <main>
        <ModeToggle />
        <Sheet
          sx={{
            width: 300,
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
          <div>
            <Typography level="h4" component="h1">
              <b>Welcome Adminn!!</b>
            </Typography>
            <Typography level="body-sm">Sign in to continue.</Typography>
          </div>
         <Form onSubmit = {handleSubmit(doLogin)}> 
         <Field label="Admin-ID" error={errors?.id}>
          <Input
            {...register("id", { required: "Admin-ID is required"})}
            type="number"
            id="id"
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
         <Button>Login</Button>
          </Form>
         {hasError && <p><b>Wrong credentials or server error.</b></p>}
        </Sheet>
      </main>
    </CssVarsProvider>
  );
}
