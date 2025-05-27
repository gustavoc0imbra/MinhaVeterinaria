import { Box } from "@mui/material";

export default function Image({ img, isRounded=false }) {
    return (
        <>
            <Box
                sx={{
                    maxWidth: "100%",
                    objectFit: "cover",
                }}
            >
                <img 
                    style={{
                        width: "100%",
                        borderRadius: isRounded ? "50%" : "none"
                    }}
                    src={img}
                />
            </Box>
        </>
    );
}