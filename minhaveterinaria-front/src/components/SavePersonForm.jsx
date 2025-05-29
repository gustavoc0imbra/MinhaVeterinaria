export default function SavePersonForm({ handleOpen, handleClose }) {
    return (
        <>
            <Dialog
                open={modalOpen}
                onClose={handleCloseModal}
                component="form"
            >
                <DialogTitle>Salvar Pessoa</DialogTitle>
                <DialogContent>
                    <TextField autoFocus fullWidth required type="text" placeholder="Digite o nome" label="Nome" margin="dense" />
                    <TextField fullWidth required type="text" placeholder="Digite o telefone" label="Telefone" margin="dense" />
                </DialogContent>
                <DialogActions>
                    <Button color="error" variant="contained" onClick={handleCloseModal}>Cancelar</Button>
                    <Button color="primary" variant="contained" onClick={handleSaveClick}>Salvar</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}