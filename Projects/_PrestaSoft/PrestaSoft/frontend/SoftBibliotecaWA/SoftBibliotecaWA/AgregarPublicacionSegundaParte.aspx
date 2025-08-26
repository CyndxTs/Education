<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="AgregarPublicacionSegundaParte.aspx.cs" Inherits="SoftBibliotecaWA.AgregarPublicacionSegundaParte" %>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <div class="d-flex align-items-center justify-content-center vh-100">
        <div class="container mt-4">
            <div class="row">
                <!-- Card Libro -->
                <div class="col-md-6">
                    <div class="card" id="cardLibro" style="cursor: pointer;" onclick="selectCard('libro')">
                        <div class="card-body">
                            <h5 class="card-title">Libro</h5>
                            <!-- Tipo de Libro -->
                            <div class="mb-3">
                                <label for="ddlTipoLibro" class="form-label">Tipo de Libro</label>
                                <asp:DropDownList ID="ddlTipoLibro" runat="server" CssClass="form-control">
                                    <asp:ListItem Value="" Text="Seleccione un tipo de libro"></asp:ListItem>
                                    <asp:ListItem Value="Enciclopedia" Text="Enciclopedia"></asp:ListItem>
                                    <asp:ListItem Value="Academico" Text="Académico"></asp:ListItem>
                                    <asp:ListItem Value="Novela" Text="Novela"></asp:ListItem>
                                </asp:DropDownList>
                            </div>
                            <!-- ISBN -->
                            <div class="col-md-6">
                                <label for="txtISBN" class="form-label">ISBN</label>
                                <asp:TextBox ID="txtISBN" runat="server" CssClass="form-control" placeholder="Ingrese el ISBN del libro"></asp:TextBox>
                            </div>
                            <!-- Género -->
                            <div class="mb-3">
                                <label for="txtGenero" class="form-label">Género</label>
                                <asp:TextBox ID="txtGenero" runat="server" CssClass="form-control" placeholder="Ingrese el género del libro si lo tuviera"></asp:TextBox>
                            </div>
                            <!-- Materia -->
                            <div class="mb-3">
                                <label for="txtMateria" class="form-label">Materia</label>
                                <asp:TextBox ID="txtMateria" runat="server" CssClass="form-control" placeholder="Ingrese la materia del libro si la tuviera"></asp:TextBox>
                            </div>

                            <!-- Volumen -->
                            <div class="mb-3">
                                <label for="txtVolumen" class="form-label">Volumen</label>
                                <asp:TextBox ID="txtVolumen" runat="server" CssClass="form-control" placeholder="Ingrese el volumen del libro si lo tuviera"></asp:TextBox>
                            </div>

                            <!-- Tomo -->
                            <div class="mb-3">
                                <label for="txtTomo" class="form-label">Tomo</label>
                                <asp:TextBox ID="txtTomo" runat="server" CssClass="form-control" placeholder="Ingrese el tomo del libro si lo tuviera"></asp:TextBox>
                            </div>
                            <!-- Botón Agregar Nueva Editorial -->
                            <div class="mt-3">
                                <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalAgregarEditorial">Agregar Editorial</button>
                                <!-- Editorial no editable y botón de búsqueda -->
                                <div class="mb-3">
                                    <div class="input-group">
                                        <asp:TextBox ID="txtEditorial" runat="server" CssClass="form-control" ReadOnly="true"></asp:TextBox>
                                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalBuscarEditorial">Buscar Editorial</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Card Tesis -->
                <div class="col-md-6">
                    <div class="card" id="cardTesis" style="cursor: pointer;" onclick="selectCard('tesis')">
                        <div class="card-body">
                            <h5 class="card-title">Tesis</h5>
                            <!--Grado Tesis-->
                            <div class="mb-3">
                                <label for="ddlTipoTesis" class="form-label">Tipo de Tesis</label>
                                <asp:DropDownList ID="ddlTipoTesis" runat="server" CssClass="form-select mx-2">
                                    <asp:ListItem Value="" Text="Seleccione un tipo de grado de tesis"></asp:ListItem>
                                    <asp:ListItem Value="TECNICO" Text="Tecnico"></asp:ListItem>
                                    <asp:ListItem Value="LICENCIATURA" Text="Licenciatura"></asp:ListItem>
                                    <asp:ListItem Value="ESPECIALIZACION" Text="Especializacion"></asp:ListItem>
                                    <asp:ListItem Value="MAESTRIA" Text="Maestria"></asp:ListItem>
                                    <asp:ListItem Value="DOCTORADO" Text="Doctorado"></asp:ListItem>
                                </asp:DropDownList>
                            </div>
                            <!--Tema-->
                            <div class="mb-3">
                                <div class="mb-3">
                                    <label for="txtTema" class="form-label">Tema</label>

                                    <div class="input-group">
                                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalBuscarTema">Buscar Tema</button>
                                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalAgregarTema">Agregar Tema</button>
                                    </div>
                                    <div>
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
                </div>
            </div>

            <!-- Botones -->
            <div class="mt-4 d-flex justify-content-between">
                <asp:Button ID="btnAnterior" runat="server" Text="Anterior" CssClass="btn btn-secondary" OnClick="btnAnterior_Click" />
                <asp:Button ID="btnGuardar" runat="server" Text="Guardar" CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
            </div>
        </div>

        <asp:HiddenField ID="hfTipoSeleccionado" runat="server" />

        <style>
            .card-selected {
                border: 2px solid #007bff;
                box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
            }
        </style>

        <script>
            function selectCard(tipo) {
                // Obtener los elementos de los cards y sus campos
                const cardLibro = document.getElementById("cardLibro");
                const cardTesis = document.getElementById("cardTesis");
                const txtEditorial = document.getElementById("<%= txtEditorial.ClientID %>");
                const hiddenField = document.getElementById("<%= hfTipoSeleccionado.ClientID %>"); // Obtén el HiddenField

                if (tipo === "libro") {
                    // Seleccionar Libro
                    cardLibro.classList.add("card-selected");
                    cardTesis.classList.remove("card-selected");

                    // Actualizar valor del HiddenField
                    hiddenField.value = "libro";

                    // Habilitar campos de Libro
                    txtEditorial.removeAttribute("disabled");

                    // Deshabilitar campos de Tesis
                    // (agrega aquí los campos correspondientes)
                } else if (tipo === "tesis") {
                    // Seleccionar Tesis
                    cardTesis.classList.add("card-selected");
                    cardLibro.classList.remove("card-selected");

                    // Actualizar valor del HiddenField
                    hiddenField.value = "tesis";

                    // Habilitar campos de Tesis
                    // (agrega aquí los campos correspondientes)
                }
            }
        </script>

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


        <div class="modal fade" id="modalAgregarEditorial" tabindex="-1" aria-labelledby="modalAgregarEditorial" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalAgregarEditorialLabel">Agregar Editorial</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulario Agregar Nueva Editorial (Oculto inicialmente) -->
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="txtNombreEditorial" class="form-label">Nombre y Apellido del Autor</label>
                                <input type="text" id="txtNombreEditorial" runat="server" class="form-control" placeholder="Ejemplo: Norma">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <asp:Button ID="btnRegistrarEditorial" runat="server" Text="Registrar" CssClass="btn btn-primary" OnClick="btnRegistrarEditorial_Click1" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal Buscar Editorial -->
        <div class="modal fade" id="modalBuscarEditorial" tabindex="-1" aria-labelledby="modalBuscarEditorialLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalBuscarEditorialLabel">Buscar Editorial</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:UpdatePanel runat="server">
                            <ContentTemplate>
                                <div class="mb-3">
                                    <label for="txtBuscarEditorial" class="form-label">Buscar Editorial</label>
                                    <div class="input-group">
                                        <asp:TextBox ID="txtBuscarEditorial" runat="server" CssClass="form-control"></asp:TextBox>
                                        <asp:Button ID="btnBuscarEditorial" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscarEditorial_Click" />
                                    </div>
                                </div>
                                <div class="container row">
                                    <asp:GridView ID="gdvBuscarEditorialModal" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                                        <Columns>
                                            <asp:BoundField HeaderText="Nomber" DataField="nombre" />
                                            <asp:TemplateField>
                                                <ItemTemplate>
                                                    <asp:LinkButton ID="lbtnSeleccionarEditorialModel" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="lbtnSeleccionarEditorialModel_Click" CommandArgument='<%# Eval("idEditorial") + "," + Eval("nombre") %>' />
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
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
