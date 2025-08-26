<%@ Page Title="" Language="C#" MasterPageFile="/MaestroMiembro.Master" AutoEventWireup="true" CodeBehind="/PrestamosMiembro.aspx.cs" Inherits="SoftBibliotecaWA.PrestamoMiembro" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    }
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
            left: 0;
        }

        .fixed-top-bar button:hover {
            background-color: #0056b3;
        }

        /* Ajuste del margen del contenido */
        .content-container {
            margin-top: 110px; /* Suma la altura de la barra negra (50px) y la beige (60px) */
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <!-- Barra fija con botones -->
    <div class="fixed-top-bar">
        <asp:Button ID="btnIzquierda" runat="server" Text="Mis Reservas" CssClass="btn btn-primary" OnClick="BtnReservas_Click" />
        <asp:Button ID="btnDerecha" runat="server" Text="Informe de Solicitudes" CssClass="btn btn-primary" OnClick="BtnInformeSolicitudes_Click" />
    </div>

    <!-- Contenido principal -->
    <div class="content-container container mt-10">
        <div class="card shadow">
            <div class="card-header bg-dark text-white">
                <h3 class="mb-0">Mis Préstamos</h3>
            </div>
            <div class="card-body">
                <asp:GridView ID="gvPrestamo" runat="server" AutoGenerateColumns="False"
                    CssClass="table table-bordered table-hover text-center"
                    EmptyDataText="No hay préstamos en curso">
                    <Columns>
                        <asp:BoundField DataField="strInstantePrestamo" HeaderText="Fecha de Préstamo" SortExpression="strInstantePrestamo"
                            ItemStyle-CssClass="align-middle" HeaderStyle-CssClass="align-middle bg-light" />
                        <asp:BoundField DataField="fechaDevolucionEsperada" HeaderText="Fecha de Fin de Préstamo" SortExpression="fechaDevolucionEsperada"
                            ItemStyle-CssClass="align-middle" HeaderStyle-CssClass="align-middle bg-light" />
                        <asp:BoundField DataField="tipoEstado" HeaderText="Estado" SortExpression="tipoEstado"
                            ItemStyle-CssClass="align-middle" HeaderStyle-CssClass="align-middle bg-light" />
                        <asp:TemplateField HeaderText="Reserva Previa" HeaderStyle-CssClass="align-middle bg-light" ItemStyle-CssClass="align-middle">
                            <ItemTemplate>
                                <%# Convert.ToBoolean(Eval("huboReservaPrevia")) ? "Sí" : "No" %>
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:BoundField DataField="ejemplarAsociado.publicacionAsociada.titulo" HeaderText="Publicación Asociada" SortExpression="ejemplarAsociado.publicacionAsociada.titulo"
                            ItemStyle-CssClass="align-middle" HeaderStyle-CssClass="align-middle bg-light" />
                        <asp:TemplateField ItemStyle-CssClass="align-middle">
                            <ItemTemplate>
                                <asp:Button ID="btnExtender" runat="server" Text="Extender" CssClass="btn btn-primary btn-sm" 
                                    CommandArgument='<%# Eval("idPrestamo") %>'
                                    OnClick="ExtenderPrestamo" />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
        </div>
    </div>

    <!-- Modal para Solicitar Ampliación -->
    <div class="modal fade" id="modalAmpliacion" tabindex="-1" aria-labelledby="modalAmpliacionLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalAmpliacionLabel">Solicitar ampliación</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <asp:Label ID="txtAmpliacion" runat="server" Text="La ampliacion será de días"></asp:Label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <asp:Button ID="btnConfirmarAmpliacion" runat="server" Text="Condirmar Ampliacion" CssClass="btn btn-primary" OnClick="ConfirmarAmpliacion_Click" />
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="modalAmpliacion1" tabindex="-1" aria-labelledby="modalAmpliacionLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title" id="modalAmpliacionLabel1">Solicitar Ampliación</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>                  
                    <div class="modal-body text-center">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <asp:Label ID="lblMensajeAmpliacion1" runat="server"></asp:Label>
                                <div class="modal-footer d-flex justify-content-between">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
                                    <asp:Button ID="btnConfirmarAmpliacion1" runat="server" Text="Confirmar ampliación" CssClass="btn btn-primary" OnClick="ConfirmarAmpliacion_Click" />
                                </div>
                            </ContentTemplate>
                        </asp:UpdatePanel>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <script type="text/javascript">
         function showModalUniversidad() {
             $('#modalAmpliacion').modal('show');
         }

         function hideModalUniversidad() {
             $('#modalAmpliacion').modal('hide');
         }
     </script>
</asp:Content>