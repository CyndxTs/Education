<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="RegistrarPrestamoBibliotecario.aspx.cs" Inherits="SoftBibliotecaWA.RegistrarPrestamoBibliotecario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />

    <!-- Encabezado -->
    <div class="bg-light py-3 px-3 border-bottom shadow-sm">
        <h3 class="mb-0 text-center text-primary fw-bold">Registrar Préstamo</h3>
    </div>

    <!-- Contenido -->
    <div id="contenido" class="container py-5">
        <!-- Formulario principal -->
        <div class="card shadow-lg border-0 rounded-3 mx-auto" style="max-width: 800px;">
            <div class="card-body">
                <!-- Inputs para consultas -->
                <div class="row g-4">
                    <!-- Código de la copia -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold" for="txtCodigoCopia">Consultar copia</label>
                        <div class="input-group">
                            <asp:TextBox ID="txtCodigoCopia" CssClass="form-control" runat="server" placeholder="Código de la copia ejemplar"></asp:TextBox>
                            <asp:Button ID="btnConsultarCopia" CssClass="btn btn-primary" runat="server" Text="Consultar" OnClick="btnConsultarCopia_Click" />
                        </div>
                    </div>
                    <!-- Buscar miembro -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Buscar miembro</label>
                        <div class="input-group">
                            <asp:TextBox ID="txtBuscarNombreMiembro" CssClass="form-control" runat="server" placeholder="Nombres"></asp:TextBox>
                            <asp:TextBox ID="txtBuscarApellidoMiembro" CssClass="form-control" runat="server" placeholder="Apellidos"></asp:TextBox>
                            <asp:Button ID="btnBuscarMiembro" CssClass="btn btn-primary" runat="server" Text="Consultar" OnClick="btnBuscarMiembro_Click" />
                            <asp:Button ID="btnEliminarMiembro" CssClass="btn btn-secondary" runat="server" Text="Eliminar" OnClick="btnEliminarMiembro_Click" />
                        </div>
                    </div>
                </div>

                <!-- Resultados de búsqueda -->
                <div class="mt-4">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="table-responsive">
                                <asp:GridView ID="gvMiembros" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover align-middle">
                                    <Columns>
                                        <asp:BoundField HeaderText="Nombre" DataField="nombres" />
                                        <asp:BoundField HeaderText="Apellidos" DataField="apellidos" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <asp:LinkButton ID="btnSeleccionarMiembro" class="btn btn-success btn-sm" runat="server" 
                                                    Text="<i class='fas fa-check'></i> Seleccionar" 
                                                    OnClick="btnSeleccionarMiembro_Click" 
                                                    CommandArgument='<%# Eval("IdUsuario") + "," + Eval("nombres") + "," + Eval("apellidos") %>' />
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                    </Columns>
                                </asp:GridView>
                            </div>

                            <!-- Detalles del miembro seleccionado -->
                            <div id="calificacionDiv" class="mt-4 text-center">
                                <h5 id="nombreUsuario" class="fw-bold" runat="server"></h5>
                                <asp:Label ID="lblMotivos" CssClass="text-muted" runat="server" Text=""></asp:Label>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>

                <!-- Información de la copia -->
                <div class="row mt-5 align-items-center text-center">
                    <div class="col-md-6">
                        <asp:Image ID="imgPortada" runat="server" CssClass="img-fluid rounded shadow-sm" Width="200px" Visible="false" />
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblIdPublicacion" CssClass="d-block fw-bold" runat="server" Text="" Visible="false"></asp:Label>
                        <asp:Label ID="lblTituloPublicacion" CssClass="d-block" runat="server" Text="" Visible="false"></asp:Label>
                    </div>
                </div>

                <!-- Botones de acción -->
                <div class="text-center mt-5">
                    <asp:Button ID="btnPrestamo" CssClass="btn btn-success btn-lg me-2" runat="server" Text="Registrar" OnClick="btnRegistrar_Click" />
                    <asp:Button ID="btnNuevoPrestamo" CssClass="btn btn-primary btn-lg" runat="server" Text="Nuevo préstamo" OnClick="btnNuevoPrestamo_Click" />
                </div>
                <div class="mt-3 text-center">
                    <asp:Label ID="lblMensajeRegistro" runat="server" Text="" CssClass="text-muted"></asp:Label>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
