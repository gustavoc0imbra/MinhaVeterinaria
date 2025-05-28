import { createTheme, ThemeProvider } from "@mui/material";
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import "../assets/base.css";

export default function BaseLayout({ children }) {
    const theme = createTheme({
        defaultColorScheme: "dark"
    });

    return (
        <>
            <ThemeProvider theme={theme}>
                {children}
            </ThemeProvider>
        </>
    );
}