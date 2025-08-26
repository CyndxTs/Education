<%@ Page Title="Catálogo" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="CatalogoBibliotecario.aspx.cs" Inherits="SoftBibliotecaWA.CatalogoBibliotecario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <style>
        .barraBusqueda {
            position: absolute;
            top: 50px; 
            left: 250px; 
            width: calc(100% - 250px);
            z-index: 999;
            background-color: #D3D3D3;
            padding: 15px; 
            margin: 0;
            transition: left 0.3s ease, width 0.3s ease;
        }

        #sidebarToggle:checked ~ .main-content .barraBusqueda {
            width: 100%; 
            left:0;
        }
    </style>

    <!-- Barra de búsqueda -->
    <div class="barraBusqueda d-flex flex-column align-items-start">
        <div class="input-group mb-3">
            <asp:TextBox ID="txtBuscar" runat="server" CssClass="form-control" placeholder="Buscar por título"></asp:TextBox>
            <asp:Button ID="btnBuscar" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscar_Click" />
            <asp:DropDownList ID="ddlBibliotecas" runat="server" CssClass="form-select mx-2" Style="padding: 4px 8px; font-size: 14px;">
            </asp:DropDownList> 
            <asp:DropDownList ID="ddlTipoPublicacion" runat="server" CssClass="form-select mx-2" Style="padding: 4px 8px; font-size: 14px;">
                <asp:ListItem Text="Seleccione tipo de publicación" Value="" />
                <asp:ListItem Text="Libro" Value="Libro" />
                <asp:ListItem Text="Tesis" Value="Tesis" />
            </asp:DropDownList>
            <buttom type="button" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#busquedaAvanzadaModal" style="text-decoration: none; color: #ffffff;">Búsqueda Avanzada
            </buttom>
        </div>

        


    </div>

    <!-- Contenido principal con publicaciones -->
    <div id="contenido" class="contenidoNormal mx-3" style="padding-top: 100px; padding-right: 40px;">
        <!-- Botón de agregar publicación a la derecha -->
        <a href="AgregarPublicacion.aspx" class="btn btn-success" role="button">
            <i class="fa-solid fa-plus me-2"></i> Agregar Publicación
        </a>
        <br />
        <br />
        <div class="row">
            <asp:Repeater ID="rptPublicaciones" runat="server" OnItemDataBound="rptPublicaciones_ItemDataBound">
                <ItemTemplate>
                    <div class="col-md-6 mb-3">
                        <div class="publicacion d-flex align-items-start border rounded p-2" style="max-width: 1000px; height: 200px;">
                            <asp:Image ID="imgPortada" runat="server" Width="110" Height="150" CssClass="me-3" />
                            <div>
                                <h4 class="mb-1">
                                    <a href='<%# "GestionPublicacion.aspx?id=" + Eval("idPublicacion") + "&tipo=" + (Container.DataItem is SoftBibliotecaBO.ServicioWeb.tesis ? "tesis" : "libro") %>' class="text-dark" style="text-decoration: none;">
                                        <%# Eval("titulo") %>
                                    </a>
                                </h4>
                                <p class="mb-0"><%# Eval("descripcion").ToString().Length > 275 ? Eval("descripcion").ToString().Substring(0,275) + "..." : Eval("descripcion") %></p>
                            </div>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>

            <!-- Paginación y botón Agregar Publicación alineados -->
            <div class="pagination mt-3 d-flex justify-content-between w-100">
                <!-- Botón "Anterior" -->
                <asp:Button ID="btnPrevious" runat="server" Text="Anterior" CssClass="btn btn-primary" OnClick="btnPrevious_Click" Style="width: 100px;" />

                <!-- Botón "Siguiente" -->
                <asp:Button ID="btnNext" runat="server" Text="Siguiente" CssClass="btn btn-primary" OnClick="btnNext_Click" Style="width: 100px;" />
            </div>
        </div>
    </div>

    <!-- Modal de búsqueda avanzada -->
    <div class="modal fade" id="busquedaAvanzadaModal" tabindex="-1" aria-labelledby="busquedaAvanzadaLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-posicionada">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="busquedaAvanzadaLabel">Búsqueda Avanzada</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="mb-3">
                                <asp:TextBox ID="txtTitulo" CssClass="form-control" placeholder="Titulo" runat="server" />
                                <asp:TextBox ID="txtDescripcion" CssClass="form-control mt-2" placeholder="Descripcion" runat="server" />
                            </div>
                            <div class="d-flex flex-column align-items-start mb-3">
                                <div class="mb-3 w-100">
                                    <asp:Label class="form-label" for="txtBuscarAutor" ID="lblTxtBuscar" runat="server" Text="Autor"></asp:Label>
                                    <div class="input-group">
                                        <asp:TextBox ID="txtBuscarAutor" runat="server" CssClass="form-control" placeholder="Ingrese el nombre del autor"></asp:TextBox>
                                        <asp:Button ID="btnBuscarAutor" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscarAutor_Click" />
                                        <asp:Button ID="btnEliminarAutorModal" CssClass="btn btn-secondary ms-2" runat="server" Text="Eliminar" OnClick="btnEliminarAutorModal_Click" />
                                    </div>
                                </div>
                                <div class="w-100">
                                    <asp:GridView ID="gvAutores" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false"
                                        CssClass="table table-hover table-responsive table-striped">
                                        <Columns>
                                            <asp:BoundField HeaderText="Nombre Completo" DataField="nombreCompleto" />
                                            <asp:TemplateField>
                                                <ItemTemplate>
                                                    <asp:LinkButton ID="btnSeleccionarAutorModa" class="btn btn-success" runat="server"
                                                        Text="Seleccionar"
                                                        OnClick="btnSeleccionarAutorModa_Click1"
                                                        CommandArgument='<%# Eval("idAutor") + "," + Eval("nombreCompleto")%>' />
                                                </ItemTemplate>
                                            </asp:TemplateField>
                                        </Columns>
                                    </asp:GridView>
                                </div>
                            </div>
                            <div class="mb-3">
                                <div class="form-check ms-3">
                                    <asp:Label for="ckcBoxHayFD" ID="lblHayFD" runat="server" Text="Tiene formato digital" class="form-check-label"></asp:Label>
                                    <asp:CheckBox ID="ckcBoxHayFD" runat="server" class="form-check-input" />
                                </div>
                                <div class="form-check ms-3">
                                    <asp:Label for="ckcBoxHayFF" ID="lblHayFF" runat="server" Text="Tiene formato fisico" class="form-check-label"></asp:Label>
                                    <asp:CheckBox ID="ckcBoxHayFF" runat="server" class="form-check-input" />
                                </div>
                                <asp:DropDownList ID="ddlBibliotecaModel" runat="server" CssClass="form-select custom-dropdown">
                                </asp:DropDownList>
                            </div>
                            <div class="d-flex mb-3">
                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="tipoPublicacion" id="tipoLibro" value="libro" runat="server">
                                    <label class="form-check-label" for="tipoLibro">Libro</label>
                                </div>
                                <div class="form-check" style="-moz-user-focus">
                                    <input class="form-check-input" type="radio" name="tipoPublicacion" id="tipoTesis" value="tesis" runat="server">
                                    <label class="form-check-label" for="tipoTesis">Tesis</label>
                                </div>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <asp:Button ID="btnBuscarModal" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscarModal_Click" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
