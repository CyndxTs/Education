<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="BibliotecarioPrestamos.aspx.cs" Inherits="SoftBibliotecaWA.BibliotecarioPrestamos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <title>Préstamos</title>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-5">
        <h2 class="mb-4 text-center">📚 Lista de Préstamos</h2>

        <asp:Repeater ID="rptPrestamos" runat="server" OnItemDataBound="rptPrestamos_ItemDataBound">
            <ItemTemplate>
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10">
                        <div class="prestamo-item border rounded shadow-sm p-4 mb-4 bg-white">
                            <div class="d-flex justify-content-between align-items-center">
                                <h5 class="text-primary mb-2">
                                    <i class="fas fa-book"></i> Código de Préstamo: 
                                    <span class="text-dark"><%# Eval("idPrestamo") %></span>
                                </h5>
                                <span class="badge 
                                    <%# Eval("tipoEstado").ToString() == "Pendiente" ? "bg-warning text-dark" : Eval("tipoEstado").ToString() == "Completado" ? "bg-success" : "bg-danger" %>">
                                    <%# Eval("tipoEstado") %>
                                </span>
                            </div>
                            <hr />
                            <ul class="list-unstyled">
                                <li><i class="fas fa-calendar-alt"></i> <strong>Fecha de Préstamo:</strong> <%# FormatearFecha(Eval("strInstantePrestamo")) %></li>
                                <li><i class="fas fa-user"></i> <strong>Usuario:</strong> <%# Eval("usuarioAsociado.nombres") + " " + Eval("usuarioAsociado.apellidos")%></li>
                                <li><i class="fas fa-book-open"></i> <strong>Publicación:</strong> <%# Eval("ejemplarAsociado.publicacionAsociada.titulo")%></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </ItemTemplate>
        </asp:Repeater>
    </div>
</asp:Content>
