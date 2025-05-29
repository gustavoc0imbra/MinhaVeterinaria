import { Box } from "@mui/material";

export default function Image({ img, isRounded=false }) {
    return (
        <>
            <img 
                style={{
                    maxWidth: "100%",
                    borderRadius: isRounded ? "50%" : "none"
                }}
                src={img}
            />
        </>
    );
}