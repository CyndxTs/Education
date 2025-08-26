<%@ Page Title="Inicio de Sesión" Language="C#" MasterPageFile="~/SoftBiblioteca.Master" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="SoftBibliotecaWA.Inicio" %>


<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Progra3 - Inicio de Sesión
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="mb-3">
        <label for="txtEmail" class="form-label">Correo Electrónico</label>
        <input type="email" class="form-control" id="txtEmail" placeholder="Ingrese su correo" required>
    </div>
    <div class="mb-3">
        <label for="txtPassword" class="form-label">Contraseña</label>
        <input type="password" class="form-control" id="txtPassword" placeholder="Ingrese su contraseña" required>
    </div>
    <asp:LinkButton ID="btnIniciar" runat="server" Text="Iniciar Sesión" CssClass="btn btn-secondary" OnClick="btnLogin_Click" />
    
    <!-- Nuevo recuadro para registrarse -->
    <div class="mt-4 text-center">
        <p>No tienes una cuenta? <a href="Registro.aspx" class="btn btn-secondary">Registrarse</a></p>
    </div>
</asp:Content>
