<%@ Page Title="Inicio de sesión" Language="C#" MasterPageFile="~/MaestroLoginReg.Master" AutoEventWireup="true" CodeBehind="InicioSesion.aspx.cs" Inherits="SoftBibliotecaWA.InicioSesion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <div class="background-image">
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
        <div class="col-md-6 col-lg-4 login-container">
            <h3 class="text-center mb-4">Iniciar Sesión</h3>
            
            <div class="form-group mb-3">
                <asp:Label ID="lblEmail" runat="server" Text="Correo electrónico" CssClass="form-label"></asp:Label>
                <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" placeholder="Ingrese correo" required></asp:TextBox>
            </div>
            <div class="form-group mb-3">
                <asp:Label ID="lblPassword" runat="server" Text="Contraseña" CssClass="form-label"></asp:Label>
                <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control" TextMode="Password" placeholder="Ingrese contraseña" required></asp:TextBox>
            </div>
            <asp:Button ID="btnIniciarSesion" CssClass="btn btn-primary w-100" runat="server" Text="Iniciar Sesión" OnClick="btnIniciarSesion_Click"/>
            <div class="text-center mt-3">
                <a href="RegistrarUsuario.aspx">¿No tienes cuenta? Registrarse</a>
            </div>
        </div>
    </div>
         </div>
</asp:Content>


