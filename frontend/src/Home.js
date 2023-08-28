import React from "react";
import { NavLink } from "react-router-dom";
import { Button } from "react-bootstrap";
import Box from '@mui/material/Box';
import Tab from '@mui/material/Tab';
import TabContext from '@mui/lab/TabContext';
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';
import { Card } from "@mui/material";
import { Sheet } from "@mui/joy";
import { styled } from '@mui/material/styles';
// import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Unstable_Grid2';
import { AspectRatio, Avatar } from "@mui/joy";
import {Stack }from "@mui/joy";


const Home = () => {
  const [value, setValue] = React.useState('1');
  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));

  const handleChange = (event, newValue) => {
    setValue(newValue);
  }
  return (
    <>
      <h2 style={{ fontFamily: "Times New Roman", color: "white" }}>Welcome to Home page</h2>
      <Box sx={{ width: '100%', typography: 'body1' }}>
        <TabContext value={value}>
          <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
            <TabList onChange={handleChange} aria-label="lab API tabs example">
              <Tab label="Our Services" value="1" style={{ color: "yellow", fontFamily: "Times New Roman" }} />
              <Tab label="Contact Us" value="2" style={{ color: "yellow", fontFamily: "Times New Roman" }} />

            </TabList>
          </Box>
          <TabPanel value="1">
            <Box sx={{ minWidth: 275 }}>
              <Grid container>
                <Grid xs={6}>
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
                    <Card variant="outlined" sx={{ width: 500 }}>
                      <AspectRatio variant="outlined">
                        <div><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Wellsfargohq.jpg/800px-Wellsfargohq.jpg" alt="wiki"></img></div>


                      </AspectRatio>
                      <div style={{ fontFamily: "Times New Roman" }}>Wells Fargo & Company is an American multinational financial services company
                        with a significant global presence.[4][3] The company operates in 35 countries and serves over 70 million
                        customers worldwide.[3] It is a systemically important financial institution according to the Financial Stability Board,
                        and is considered one of the "Big Four Banks" in the United States, alongside JPMorgan Chase, Bank of America, and Citigroup.[5]

                      </div>
                    </Card>
                  </Sheet>
                </Grid>
                <Grid xs>
                  <Stack spacing={6}>
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
                    <div className="container d-flex align-items-left flex-column" style={{ color: "white" }} >
                      <nav>
                        <h4 style={{ fontFamily: "Times New Roman", color: "Black" }}> Go to your Account</h4>
                        <NavLink to="/login">
                          <Button sx={{ mt: 1 /* margin top */ }} >Login</Button></NavLink>
                        <p style={{ color: "black" }}>Not registered yet ?
                          <NavLink to="/register">
                            Register</NavLink></p>
                      </nav>
                    </div>
                  </Sheet>

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
                    <div className="container d-flex align-items-left flex-column" style={{ color: "white" }} >
                      <nav>
                        <h4 style={{ fontFamily: "Times New Roman", color: "Black" }}> Welcom Admin !</h4>
                        <NavLink to="/adminlogin">
                          <Button sx={{ mt: 1 /* margin top */ }} >Login</Button></NavLink>
                        
                      </nav>
                    </div>
                  </Sheet>
                  </Stack>
                </Grid>
              </Grid>

            </Box>


          </TabPanel>
          <TabPanel value="2" >
            <Box sx={{ flexGrow: 1 }}>
              <Grid container spacing={3}>

                <Grid xs={6}>
                  {/* <Item>xs=6</Item> */}
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
                    <p> <b>Customer service</b>: 000 800 001 6090</p>
                    <p ><b>Headquarters</b>: San Francisco, California, United States</p>
                   
                    <p><b>CEO</b>: Charles W. Scharf (21 Oct 2019–)</p>
                    <p><b>COO</b>: Scott Powell</p>
                    <p><b>Founde</b>r: William Fargo</p>
                    <p><b>Founded</b>: 1852, New York, New York, United States</p>
                    <p><b>President</b>: Charles W. Scharf</p>

                  </Sheet>
                </Grid>
                <Grid xs>
                  <Stack spacing={6}>
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
                    <div className="container d-flex align-items-left flex-column" style={{ color: "white" }} >
                      <nav>
                        <h4 style={{ fontFamily: "Times New Roman", color: "Black" }}> Go to your Account</h4>

                        <NavLink to="/login">
                          <Button sx={{ mt: 1 /* margin top */ }} >Login</Button></NavLink>
                        <p style={{ color: "black" }}>Not registered yet ?
                          <NavLink to="/register">
                            Register</NavLink></p>
                      </nav>
                    </div>
                  </Sheet>
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
                    <div className="container d-flex align-items-left flex-column" style={{ color: "white" }} >
                      <nav>
                        <h4 style={{ fontFamily: "Times New Roman", color: "Black" }}> Welcome Admin !</h4>
                        <NavLink to="/adminlogin">
                          <Button sx={{ mt: 1 /* margin top */ }} >Login</Button></NavLink>
                        
                      </nav>
                    </div>
                  </Sheet>
                  </Stack>
                </Grid>
              </Grid>
            </Box>
          </TabPanel>

        </TabContext>
      </Box>




    </>
  )
}
export default Home;