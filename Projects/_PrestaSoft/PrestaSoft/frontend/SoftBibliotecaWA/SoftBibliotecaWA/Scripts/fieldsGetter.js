function abrirModal_Consorcio(btn) {
    // Asigna el valor al HiddenField
    var idConsorcio = btn.getAttribute('CommandArgument');
    var action = btn.getAttribute('CommandName')
    document.getElementById('<%= HF_ConsorcioID.ClientID %>').value = idConsorcio;
    // Muestra el modal
    if (action == 'Editar') {
        $('#ModalEditarConsorcio').modal('show');
    }
    else if (action == 'Eliminar') {
        $('#ModalEliminarConsorcio').modal('show');
    }
}
