import { AppBar, Card, CardContent, CardMedia, Container, Drawer, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Toolbar } from "@mui/material";
import BaseLayout from "../layouts/BaseLayout";
import MenuIcon from '@mui/icons-material/Menu';
import { People } from "@mui/icons-material";
import { useState } from "react";
import img from "../assets/cacau.jpg";

export default function Dashboard() {
    const [menuOpen, setMenuOpen] = useState(false);

    const handleOpenMenu = () => {
        setMenuOpen(true);
    }

    const handleCloseMenu = () => {
        setMenuOpen(false);
    }

    return (
        <>
            <BaseLayout>
                <AppBar position="static" component="nav">
                    <Toolbar>
                        <IconButton onClick={handleOpenMenu}>
                            <MenuIcon />
                        </IconButton>
                    </Toolbar>
                </AppBar>
                <nav>
                    <Drawer
                        variant="temporary"
                        open={menuOpen}
                        onClose={handleCloseMenu}
                        ModalProps={{
                            keepMounted: true
                        }}
                    >
                        <List>
                            <ListItemButton>
                                <ListItemIcon>
                                    <People />
                                </ListItemIcon>
                                <ListItemText primary="People" />
                            </ListItemButton>
                        </List>
                    </Drawer>
                </nav>
                <Container>
                    <Card>
                        <CardMedia
                            component={"img"}
                            height="140"
                            image={img}
                        />
                        <CardContent>
                            Aee
                        </CardContent>
                    </Card>
                </Container>
            </BaseLayout>

            
        </>
    );
}