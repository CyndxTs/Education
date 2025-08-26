<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="ModificarPublicacionTesis.aspx.cs" Inherits="SoftBibliotecaWA.ModificarPublicacionTesis" %>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <div class="w-100 py-3 px-3" style="height: 60px; background-color: #F5F5DC; color: black;">
        <h5 class="mb-0 fw-bold fs-3">Modificar publicación (tesis)</h5>
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

        <!-- Tabla de Copias (visible solo si el libro es físico o ambos) -->
        <div id="divCopias" runat="server" visible="false" class="mt-4">
            <h4>Copias de la Publicación</h4>
            <asp:GridView ID="gvCopias" runat="server" AutoGenerateColumns="False" CssClass="table table-bordered"
                DataKeyNames="idCopiaEjemplar" OnRowEditing="gvCopias_RowEditing" OnRowUpdating="gvCopias_RowUpdating"
                OnRowCancelingEdit="gvCopias_RowCancelingEdit">
                <Columns>
                   
                    <asp:BoundField DataField="idCopiaEjemplar" HeaderText="ID Copia Ejemplar" ReadOnly="True" />
                    <asp:TemplateField HeaderText="Estado del Ejemplar">
                        <EditItemTemplate>
                            <asp:DropDownList ID="ddlEstado" runat="server">
                                <asp:ListItem Text="Óptimo" Value="OPTIMO" />
                                <asp:ListItem Text="Dañado" Value="DANIADO" />
                                <asp:ListItem Text="Perdido" Value="PERDIDO" />
                                <asp:ListItem Text="No disponible" Value="NODISPONIBLE" />
                            </asp:DropDownList>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="lblEstado" runat="server" 
                                       Text='<%# MapearEstadoEjemplar(Eval("estado").ToString()) %>'>
                            </asp:Label>
                        </ItemTemplate>

                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Acciones">
                        <ItemTemplate>
                            <asp:LinkButton ID="lbEditar" runat="server" CommandName="Edit" CssClass="btn btn-primary btn-sm">
                                Editar
                            </asp:LinkButton>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:LinkButton ID="lbActualizar" runat="server" CommandName="Update" CssClass="btn btn-success btn-sm">
                                Actualizar
                            </asp:LinkButton>
                            <asp:LinkButton ID="lbCancelar" runat="server" CommandName="Cancel" CssClass="btn btn-secondary btn-sm">
                                Cancelar
                            </asp:LinkButton>
                        </EditItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
            <!-- Botón para agregar nuevas copias -->
            <asp:Button ID="btnAgregarCopia" runat="server" Text="Agregar Copia" CssClass="btn btn-success mt-2" OnClick="btnAgregarCopia_Click" />
            <!-- GridView para nuevas copias -->
            <asp:GridView ID="gvCopiasNuevas" runat="server" AutoGenerateColumns="False" CssClass="table table-bordered"
                OnRowDeleting="gvCopiasNuevas_RowDeleting">
                <Columns>
                    <asp:TemplateField HeaderText="Copia Nueva">
                        <ItemTemplate>
                            <%# Container.DataItemIndex + 1 %>
                        </ItemTemplate>
                    </asp:TemplateField>

                    
                    <asp:TemplateField HeaderText="Estado del Ejemplar">
                        <ItemTemplate>
                            OPTIMO
                        </ItemTemplate>
                    </asp:TemplateField>

                    
                    <asp:TemplateField HeaderText="Acciones">
                        <ItemTemplate>
                            <asp:LinkButton ID="lbEliminarNuevaCopia" runat="server" CommandName="Delete" CommandArgument='<%# Container.DataItemIndex %>' CssClass="btn btn-danger btn-sm">
                                Eliminar
                            </asp:LinkButton>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
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
    <!-- cosas propias de  tesis  -->
    <div class="container mt-4">
        <div class="row">
            <!-- Card Tesis -->
            <div class="col-md-6">
                <div class="card" id="cardTesis" style="cursor: pointer;" onclick="selectCard('tesis')">
                    <div class="card-body">
                        <h5 class="card-title">Tesis</h5>
                        <!-- GradoAcadémico de tesis -->
                        <div class="mb-3">
                            <label for="ddlGradoTesis" class="form-label">Grado académico</label>
                            <asp:DropDownList ID="ddlGradoTesis" runat="server" CssClass="form-control">
                                <asp:ListItem Value="" Text="Seleccione un grado"></asp:ListItem>
                                <asp:ListItem Value="TECNICO" Text="Técnico"></asp:ListItem>
                                <asp:ListItem Value="LICENCIATURA" Text="Licenciatura"></asp:ListItem>
                                <asp:ListItem Value="ESPECIALIZACION" Text="Especialización"></asp:ListItem>
                                <asp:ListItem Value="MAESTRIA" Text="Maestría"></asp:ListItem>
                                <asp:ListItem Value="DOCTORADO" Text="Doctorado"></asp:ListItem>
                            </asp:DropDownList>
                        </div>

                        <!-- Temas -->
                        <div class="col-12">
                            <label for="txtTema" class="form-label">Temas</label>
                            <div class="d-flex">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAddTema">Registrar tema</button>
                                <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalBuscarTema">Buscar tema</button>
                            </div>
                            <asp:Repeater ID="rptTemas" runat="server" OnItemCommand="rptTemas_ItemCommand">
                                <HeaderTemplate>
                                    <table class="table mt-3">
                                </HeaderTemplate>
                                <ItemTemplate>
                                    <tr>
                                        <td><%# Eval("titulo") %></td>
                                        <td>
                                            <asp:Button ID="btnEliminarTema" runat="server" Text="Eliminar" CssClass="btn btn-danger" CommandName="Eliminar" CommandArgument='<%# Eval("idTema") %>' />
                                        </td>
                                    </tr>
                                </ItemTemplate>
                                <FooterTemplate>
                                    </table>
                                </FooterTemplate>
                            </asp:Repeater>
                        </div>

                       
                    </div>
                </div>
            </div>
        </div>
        <!-- buscar tema en BD -->
       <div class="modal fade" id="modalBuscarTema" tabindex="-1" aria-labelledby="modalBuscarTemaLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalBuscarTemaLabel">Buscar Tema</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="mb-3">
                                    <label for="txtBuscarTema" class="form-label">Buscar Tema</label>
                                    <div class="input-group">
                                        <asp:TextBox ID="txtBuscarTema" runat="server" CssClass="form-control"></asp:TextBox>
                                        <asp:Button ID="btnBuscarTema" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscarTema_Click" />
                                    </div>
                                </div>
                                <div class="container row">
                                    <asp:GridView ID="ModalTemas_gvTemas" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                                        <Columns>
                                            <asp:BoundField HeaderText="titulo" DataField="titulo" />
                                            <asp:TemplateField>
                                                <ItemTemplate>
                                                    <asp:LinkButton ID="modalBuscarTema" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="modalSeleccionarTema_Click" CommandArgument='<%# Eval("idTema") + "," + Eval("titulo") %>' />
                                                </ItemTemplate>
                                            </asp:TemplateField>
                                        </Columns>
                                    </asp:GridView>
                                </div>
                            </ContentTemplate>
                        </asp:UpdatePanel>
                    </div>
                </div>
            </div>
        </div>
        <!-- registrar tema en BD -->
        <div class="modal fade" id="modalAgregarTema" tabindex="-1" aria-labelledby="modalAgregarTema" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalAgregarTemaLabel">Agregar Tema</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div id="formAgregarTema" class="mt-3">
                            <label for="txtNuevoTema" class="form-label">Nombre del nuevo Tema</label>
                            <asp:TextBox ID="txtNuevoTema" runat="server" CssClass="form-control"></asp:TextBox>
                            <div class="mt-2 d-flex justify-content-between">
                                <asp:Button ID="btnRegistrarTema" runat="server" Text="Registrar" CssClass="btn btn-primary" OnClick="btnRegistrarTema_Click" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>






        
        <div class="col-12 d-flex justify-content-between mt-3">
            <asp:Button ID="btnVolver" runat="server" Text="Volver" CssClass="btn btn-secondary" OnClick="btnVolver_Click" />
            <asp:Button ID="btnModificar" runat="server" Text="Modificar" CssClass="btn btn-primary" OnClick="btnModificar_Click" />
        </div>

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
