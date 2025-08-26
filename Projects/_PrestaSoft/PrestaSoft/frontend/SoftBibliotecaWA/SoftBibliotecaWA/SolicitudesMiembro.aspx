<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SolicitudesMiembro.aspx.cs" Inherits="SoftBibliotecaWA.SolicitudesMiembro" MasterPageFile="~/MaestroMiembro.master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" Runat="Server">
    <style>
        /* Barra fija */
        .fixed-top-bar {
            position: fixed;
            top: 50px; /* Espacio para que quede debajo de la barra negra */
            width: calc(100% - 250px);
            background-color: #f5f5dc; /* Color beige */
            z-index: 999; /* Asegura que esté encima del contenido, pero debajo de la barra negra */
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            margin: 0;
            transition: left 0.3s ease, width 0.3s ease;
        }
        #sidebarToggle:checked ~ .main-content .fixed-top-bar {
            width: 100%; 
            left:0;
        }


        .fixed-top-bar button:hover {
            background-color: #0056b3;
        }

        /* Ajuste del margen del contenido */
        .content-container {
            margin-top: 110px; /* Suma la altura de la barra negra (50px) y la beige (60px) */
        }
    </style>


    <!-- Barra fija con botones -->
    <div class="fixed-top-bar">
        <asp:Button ID="btnIzquierda" runat="server" Text="Mis Préstamos" CssClass="btn btn-primary" OnClick="BtnPrestamos_Click" />
        <asp:Button ID="btnDerecha" runat="server" Text="Informe de Solicitudes" CssClass="btn btn-primary" OnClick="BtnInformeSolicitudes_Click" />
    </div>

    <!-- Contenido principal -->
    <div class="content-container container mt-10">
        <div class="card shadow mb-5">
            <div class="card-header bg-dark text-white">
                <h3 class="mb-0">Mis Reservas</h3>
            </div>
            <div class="card-body">
                <asp:GridView ID="gvReservas" runat="server" AutoGenerateColumns="False" 
                              CssClass="table table-bordered table-hover text-center" 
                              EmptyDataText="No hay reservas en curso">
                    <Columns>
                        <asp:BoundField DataField="str_InstanteReserva" HeaderText="Fecha de Reserva" SortExpression="str_InstanteReserva" 
                                        ItemStyle-CssClass="align-middle" HeaderStyle-CssClass="align-middle bg-light" />
                        <asp:BoundField DataField="TipoEstado" HeaderText="Estado" SortExpression="TipoEstado" 
                                        ItemStyle-CssClass="align-middle" HeaderStyle-CssClass="align-middle bg-light" />
                        <asp:BoundField DataField="ejemplarAsociado.publicacionAsociada.titulo" HeaderText="Publicación Asociada" SortExpression="ejemplarAsociado.publicacionAsociada.titulo" 
                                        ItemStyle-CssClass="align-middle" HeaderStyle-CssClass="align-middle bg-light" />
                        <asp:TemplateField ItemStyle-CssClass="align-middle">
                            <ItemTemplate>
                                <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-danger btn-sm" OnClick="CancelarReserva" CommandArgument='<%# Eval("idReserva") %>' />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
        </div>
</asp:Content>

