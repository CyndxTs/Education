<%@ Page Title="Configuración de Usuario" Language="C#" MasterPageFile="~/MaestroBibliotecario.master" AutoEventWireup="true" CodeBehind="ConfigCuentaBibliotecario.aspx.cs" Inherits="SoftBibliotecaWA.ConfigCuentaBibliotecario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-5">
        <div class="card text-white bg-dark" style="max-width: 500px; margin: auto;"> 
            <div class="card-body">
                <h5 class="card-title">Información Personal</h5>
          
                <div id="formConfigUsuario">
                    <div class="mb-3">
                        <label for="txtNombreUsuario" class="form-label">Nombre de Usuario</label>
                        <asp:TextBox ID="txtNombreUsuario" CssClass="form-control" runat="server" ReadOnly="True"></asp:TextBox>
                    </div>
                    <div class="mb-3">
                        <label for="txtNombres" class="form-label">Nombres</label>
                        <asp:TextBox ID="txtNombres" CssClass="form-control" runat="server" ReadOnly="True"></asp:TextBox>
                    </div>
                    <div class="mb-3">
                        <label for="txtApellidos" class="form-label">Apellidos</label>
                        <asp:TextBox ID="txtApellidos" CssClass="form-control" runat="server" ReadOnly="True"></asp:TextBox>
                    </div>
                    <div class="mb-3">
                        <label for="txtCorreo" class="form-label">Correo Institucional</label>
                        <asp:TextBox ID="txtCorreo" CssClass="form-control" runat="server" ReadOnly="True"></asp:TextBox>
                    </div>
                    <div class="mb-3">
                        <label for="txtContrasena" class="form-label">Contraseña</label>
                        <div class="input-group">
                            <asp:TextBox ID="txtContrasena" CssClass="form-control" runat="server" ReadOnly="True"></asp:TextBox>
                            <asp:Button ID="btnMostrar" runat="server" Text="Mostrar" CssClass="btn btn-outline-secondary" OnClick="btnMostrar_Click" />
                        </div>
                    </div>

                
                    <div class="text-center">
                        <asp:Button ID="btnModificar" CssClass="btn btn-secondary mt-3" runat="server" Text="Modificar" OnClick="btnModificar_Click" />
                        <asp:Button ID="btnGuardarCambios" CssClass="btn btn-primary mt-3" runat="server" Text="Guardar Cambios" OnClick="btnGuardarCambios_Click" Visible="False" />
                        <asp:Button ID="btnCancelar" CssClass="btn btn-danger mt-3" runat="server" Text="Cancelar" OnClick="btnCancelar_Click" Visible="False" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
