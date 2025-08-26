<%@ Page Title="Registro" Language="C#" MasterPageFile="~/SoftBiblioteca.Master" AutoEventWireup="true" CodeBehind="Registro.aspx.cs" Inherits="SoftBibliotecaWA.Registro" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
     Registro de Usuario
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="mb-3 row">
        <label for="txtNombre" class="col-sm-2 col-form-label">Nombre</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="txtNombre" placeholder="Ingrese su nombre" required>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="txtApellidoPaterno" class="col-sm-2 col-form-label">Apellido Paterno</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="txtApellidoPaterno" placeholder="Ingrese su apellido paterno" required>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="txtApellidoMaterno" class="col-sm-2 col-form-label">Apellido Materno</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="txtApellidoMaterno" placeholder="Ingrese su apellido materno" required>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="txtEmail" class="col-sm-2 col-form-label">Correo Electrónico</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" id="txtEmail" placeholder="Ingrese su correo" required>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="txtPassword" class="col-sm-2 col-form-label">Contraseña</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="txtPassword" placeholder="Ingrese su contraseña" required>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="txtUsername" class="col-sm-2 col-form-label">Username</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="txtUsername" placeholder="Ingrese su username" required>
        </div>
    </div>
    <div class="mb-3 row">
        <label for="ddlUniversidad" class="col-sm-2 col-form-label">Universidad</label>
        <div class="col-sm-9">
            <asp:DropDownList ID="ddlUniversidad" runat="server" CssClass="form-select" required EnableViewState="true">
                <asp:ListItem Text="Seleccione una universidad" Value=""></asp:ListItem>
            </asp:DropDownList>
        </div>
        <div class="col-sm-1">
            <asp:Button ID="btnAgregarUniversidad" runat="server" Text="+" CssClass="btn btn-secondary" OnClick="btnAgregarUniversidad_Click" />
        </div>
    </div>
    <div class="mb-3 row" id="universidad2Container" runat="server" visible="false">
        <label for="ddlUniversidad2" class="col-sm-2 col-form-label">Universidad 2</label>
        <div class="col-sm-10">
            <asp:DropDownList ID="ddlUniversidad2" runat="server" CssClass="form-select">
                <asp:ListItem Text="Seleccione una universidad" Value=""></asp:ListItem>
            </asp:DropDownList>
        </div>
    </div>
    <div class="mb-3 row">
        <div class="col-sm-10 offset-sm-2">
            <button type="submit" class="btn btn-primary">Registrarse</button>
            <asp:LinkButton ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="btnCancelar_Click" />
        </div>
    </div>
</asp:Content>
