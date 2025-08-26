<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroMiembro.Master" AutoEventWireup="true" CodeBehind="InformeSolicitudes.aspx.cs" Inherits="SoftBibliotecaWA.WebForm1" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
        <div class="container mt-5">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title text-center">Mi informe de solicitudes</h5>
                    <!-- Rango de fechas -->
                    <div class="mb-3">
                        <label for="txtFechaDel" class="form-label">Del:</label>
                        <div class="input-group">
                            <input type="date" class="form-control" id="txtFechaDel" runat="server" />
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="txtFechaHasta" class="form-label">Hasta:</label>
                        <div class="input-group">
                            <input type="date" class="form-control" id="txtFechaHasta" runat="server" />
                        </div>
                    </div>
                    <!-- Mostrar reservas -->
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="chkMostrarReservas" runat="server" />
                        <label class="form-check-label" for="chkMostrarReservas">Mostrar reservas</label>
                    </div>
                    <!-- Estado de préstamos -->
                    <div class="mb-3">
                        <label for="ddlEstadoPrestamos" class="form-label">Estado de préstamos a mostrar:</label>
                            <asp:DropDownList 
                                ID="ddlEstadoPrestamos" 
                                runat="server" 
                                CssClass="form-select">
                                <asp:ListItem Value="">Seleccione una opción o déjela vacía</asp:ListItem>
                                <asp:ListItem Value="CANCELADOPORPERDIDA">Cancelado por pérdida</asp:ListItem>
                                <asp:ListItem Value="PORDEVOLVER">Por devolver</asp:ListItem>
                                <asp:ListItem Value="DEVUELTO">Devuelto</asp:ListItem>
                                <asp:ListItem Value="ATRASADO">Atrasado</asp:ListItem>
                            </asp:DropDownList>
                    </div>
                    <!-- Universidad -->
                    <!-- DropDownList para las universidades -->
                    <div class="mb-3">
                        <label for="ddlUniversidad" class="form-label">Universidad:</label>
                        <asp:DropDownList ID="ddlUniversidad" runat="server" CssClass="form-select">
                            <asp:ListItem Value="0">Seleccione una universidad</asp:ListItem>
                            
                        </asp:DropDownList>
                    </div>

                    <!-- Botón para descargar informe -->
                    <div class="text-center">
                        <asp:Button ID="btnDescargarInforme" CssClass="btn btn-primary w-100" runat="server" Text="Descargar informe" OnClick="btnDescargarInforme_Click"/>
                        
                    </div>
                </div>
            </div>
        </div>
</asp:Content>
