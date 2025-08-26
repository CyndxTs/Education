<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="AgregarPublicacion.aspx.cs" Inherits="SoftBibliotecaWA.AgregarPublicacion" %>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <div class="w-100 py-3 px-3" style="height: 60px; background-color: #F5F5DC; color: black;">
        <h5 class="mb-0 fw-bold fs-3">Registrar Nueva Publicación</h5>
    </div>
    <div class="container mt-4">
        <!-- Título -->
        <div class="col-12">
            <label for="txtTitulo" class="form-label">Título</label>
            <input type="text" id="txtTitulo" runat="server" class="form-control" placeholder="Título de la publicacion">
        </div>

        <!-- Autores -->
        <div class="col-12">

            <label for="txtAutor" class="form-label">Autores</label>
            <div class="d-flex">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAddAutor">Registrar autor</button>
                <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalBuscarAutor">Buscar autor</button>
            </div>
            <asp:Repeater ID="rptAutores" runat="server" OnItemCommand="rptAutores_ItemCommand">
                <HeaderTemplate>
                    <table class="table mt-3">
                </HeaderTemplate>
                <ItemTemplate>
                    <tr>
                        <td><%# Eval("nombreCompleto") %></td>
                        <td>
                            <asp:Button ID="btnEliminar" runat="server" Text="Eliminar" CssClass="btn btn-danger" CommandName="Eliminar" CommandArgument='<%# Eval("idAutor") %>' />
                        </td>
                    </tr>
                </ItemTemplate>
                <FooterTemplate>
                    </table>
                </FooterTemplate>
            </asp:Repeater>
        </div>

        <!-- Fecha de publicación -->
        <div class="col-md-6">
            <label for="txtFecha" class="form-label">Fecha de Publicación</label>
            <input type="date" id="txtFecha" runat="server" class="form-control">
        </div>

        <!-- Descripción -->
        <div class="col-12">
            <label for="txtDescripcion" class="form-label">Descripción</label>
            <textarea id="txtDescripcion" runat="server" class="form-control" rows="3" placeholder="Descripción del libro"></textarea>
        </div>

        <!-- Tipo de Libro -->
        <div class="col-md-6">
            <label for="ddlTipoLibro" class="form-label">Formato de publicación</label>
            <asp:DropDownList ID="ddlTipoLibro" runat="server" CssClass="form-select" AutoPostBack="True" OnSelectedIndexChanged="ddlTipoLibro_SelectedIndexChanged">
                <asp:ListItem Value="Selecccionar" Text="Seleccionar Tipo" />
                <asp:ListItem Value="Físico" Text="Físico" />
                <asp:ListItem Value="Digital" Text="Digital" />
                <asp:ListItem Value="Ambos" Text="Ambos" />
            </asp:DropDownList>
        </div>

        <!-- Número de Copias Disponibles (visible solo si es físico o ambos) -->
        <div class="col-md-6" id="divNumCopias" runat="server" visible="false">
            <label for="txtNumCopias" class="form-label">Número de Copias Disponibles</label>
            <input type="number" id="Number1" runat="server" class="form-control">
        </div>

        <!-- URL (visible solo si es virtual o ambos) -->
        <div class="col-md-6" id="divURL" runat="server" visible="false">
            <label for="txtURL" class="form-label">URL</label>
            <input type="url" id="txtURL" runat="server" class="form-control" placeholder="Ingresar URL del libro">
        </div>

        <!-- Biblioteca -->
        <div class="col-md-6">
            <label for="ddlBiblioteca" class="form-label">Biblioteca</label>
            <asp:DropDownList ID="ddlBibliotecas" runat="server" CssClass="form-select mx-2" Style="padding: 4px 8px; font-size: 14px;">
            </asp:DropDownList>
        </div>

        <!-- CARGA DE IMAGEN -->
        <label for="txtPortada" class="form-label">Portada</label>
        <asp:FileUpload ID="fuImagen" runat="server" CssClass="form-control" />
        <asp:Button ID="btnSubirImagen" runat="server" Text="Subir Imagen" CssClass="btn btn-primary mt-2" OnClick="btnSubirImagen_Click" />
        <asp:Label ID="lblImagenCargada" runat="server" CssClass="text-success mt-2" Visible="false"></asp:Label>
        <asp:Image ID="imgPrevisualizar" runat="server" Visible="false" CssClass="img-thumbnail mt-2" Width="200px" />

        <div class="col-12 d-flex justify-content-between mt-3">
            <!-- Botón Volver -->
            <asp:Button ID="btnVolver" runat="server" Text="Volver" CssClass="btn btn-secondary" OnClick="btnVolver_Click" />
            <!-- Botón Siguiente -->
            <asp:Button ID="btnSiguiente" runat="server" Text="Siguiente" CssClass="btn btn-primary" OnClick="btnSiguiente_Click" />
        </div>
    </div>

    <!--Modadl para registrar autor-->
    <div class="modal fade" id="modalAddAutor" tabindex="-1" aria-labelledby="modalAddAutorLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalAddAutorLabel">Añadir Nuevo Autor</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="txtNombreAutor" class="form-label">Nombre y Apellido del Autor</label>
                        <input type="text" id="txtNombreAutor" runat="server" class="form-control" placeholder="Ejemplo: César Vallejo">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <asp:Button ID="btnRegistrarAutor" runat="server" Text="Registrar" CssClass="btn btn-primary" OnClick="btnRegistrarAutor_Click" />

                </div>
            </div>
        </div>
    </div>

    <!--Modadl para buscar autor-->
    <div class="modal fade" id="modalBuscarAutor" tabindex="-1" aria-labelledby="modalBuscarAutorLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalBuscarAutorLabel">Buscar Autor</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="mb-3">
                                <label for="txtBuscarAutor" class="form-label">Buscar Autor</label>
                                <div class="input-group">
                                    <asp:TextBox ID="txtBuscarAutorModal" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:Button ID="btnBuscarAutorModal" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscarAutorModal_Click" />
                                </div>
                            </div>
                            <div class="container row">
                                <asp:GridView ID="gdvAutorModal" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                                    <Columns>
                                        <asp:BoundField HeaderText="Nombre Autor" DataField="nombreCompleto" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <asp:LinkButton ID="lbtnSeleccionarAutorModal" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="lbtnSeleccionarAutorModal_Click" CommandArgument='<%# Eval("idAutor") + "," + Eval("nombreCompleto") %>' />
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                    </Columns>
                                </asp:GridView>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
                    <button type="button" class="btn btn-primary">Guardar</button>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
