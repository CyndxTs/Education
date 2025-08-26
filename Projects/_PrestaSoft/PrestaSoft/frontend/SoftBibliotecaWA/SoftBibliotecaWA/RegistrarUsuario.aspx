<%@ Page Title="Registrarse" Language="C#" MasterPageFile="~/MaestroLoginReg.Master" AutoEventWireup="true" CodeBehind="RegistrarUsuario.aspx.cs" Inherits="SoftBibliotecaWA.RegistrarUsuario" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="d-flex justify-content-center align-items-center min-vh-100">
        <div class="card shadow" style="width: 50%;">
            <div class="card-header text-center">
                <h4 class="mb-0">Crear Usuario</h4>
            </div>
            <div class="card-body">
                <div class="mb-3 row">
                    <asp:Label ID="lblNombre" runat="server" Text="Nombre:" CssClass="col-sm-4 col-form-label"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Ingresa tu nombre"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblApePaterno" runat="server" Text="Apellido Paterno:" CssClass="col-sm-4 col-form-label"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtApePaterno" runat="server" CssClass="form-control" placeholder="Ingresa tu apellido paterno"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblApeMaterno" runat="server" Text="Apellido Materno:" CssClass="col-sm-4 col-form-label"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtApeMaterno" runat="server" CssClass="form-control" placeholder="Ingresa tu apellido materno"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblCorreo" runat="server" Text="Correo Institucional:" CssClass="col-sm-4 col-form-label"></asp:Label>
                    <div class="col-sm-8 d-flex">
                        <asp:TextBox ID="txtCorreoParte1" runat="server" CssClass="form-control" placeholder="Ingresa tu correo" style="width: 60%;"></asp:TextBox>
                        <asp:DropDownList ID="ddlCorreoParte2" runat="server" CssClass="form-select" style="width: 40%;"></asp:DropDownList>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblNombreUsuario" runat="server" Text="Nombre de Usuario:" CssClass="col-sm-4 col-form-label"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtNombreUsuario" runat="server" CssClass="form-control" placeholder="Ingresa tu nombre de usuario"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblContrasenia" runat="server" Text="Contraseña:" CssClass="col-sm-4 col-form-label"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtContrasenia" runat="server" CssClass="form-control" TextMode="Password" placeholder="Ingresa tu contraseña"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblContraseniaConfirmacion" runat="server" Text="Confirmar Contraseña:" CssClass="col-sm-4 col-form-label"></asp:Label>
                    <div class="col-sm-8">
                        <asp:TextBox ID="txtContraseniaConfirmacion" runat="server" CssClass="form-control" TextMode="Password" placeholder="Confirma tu contraseña"></asp:TextBox>
                    </div>
                </div>
            </div>
            <div class="card-footer d-flex justify-content-between">
                <asp:Button ID="btnRegresar" CssClass="btn btn-primary" runat="server" Text="Regresar" OnClick="btnRegresar_Click"/>
                <asp:Button ID="btnRegistrar" CssClass="btn btn-success" runat="server" Text="Guardar" OnClick="btnRegistrar_Click"/>
            </div>
        </div>
    </div>
</asp:Content>



