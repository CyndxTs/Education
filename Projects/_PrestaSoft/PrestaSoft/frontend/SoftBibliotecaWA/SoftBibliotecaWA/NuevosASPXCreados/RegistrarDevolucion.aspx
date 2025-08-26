<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="RegistrarDevolucion.aspx.cs" Inherits="SoftBibliotecaWA.NuevosASPXCreados.WebForm1" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="row">
        <!-- Card Izquierdo -->
        <div class="col-md-6 d-flex justify-content-end">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Card Izquierda</h5>
                    <p class="card-text">Contenido del card a la izquierda.</p>
                    <a href="#" class="btn btn-primary">Ir a algún lugar</a>
                </div>
            </div>
        </div>

        <!-- Card Derecho -->
        <div class="col-md-6 d-flex justify-content-start">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Card Derecha</h5>
                    <p class="card-text">Contenido del card a la derecha.</p>
                    <a href="#" class="btn btn-primary">Ir a algún lugar</a>
                </div>
            </div>
        </div>
    </div>
</div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
