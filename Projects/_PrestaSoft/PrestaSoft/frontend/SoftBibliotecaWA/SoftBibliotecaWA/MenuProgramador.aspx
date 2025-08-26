<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroProgramador.Master" AutoEventWireup="true" CodeBehind="MenuProgramador.aspx.cs" Inherits="SoftBibliotecaWA.MenuProgramador" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
    <style>
        .gray-bar {
            background-color: #d3d3d3;
            padding: 10px;
            text-align: center;
        }

        .fixed-bar {
            position: sticky;
            top: 50px; /* Ajusta este valor según el tamaño de tu barra negra */
            z-index: 1000;
        }
    </style>

    <style>
        .btn-gold {
            background-color: #5d5f21; /* Color dorado */
            color: #ffffff; /* Color negro para el texto */
            border-color: #FFD700; /* Borde dorado */
        }

            .btn-gold:hover {
                background-color: #FFC107; /* Color dorado oscuro para hover */
                border-color: #FFC107;
                color: #000000; /* Mantener el texto negro */
            }
    </style>
    <div class="gray-bar fixed-bar">
        <asp:Button ID="btnUniversidad" runat="server" Text="Universidades" CssClass="btn btn-gold" OnClick="MostrarUniversidad" />
        <asp:Button ID="btnBiblioteca" runat="server" Text="Bibliotecas" CssClass="btn btn-gold" OnClick="MostrarBibliotecas" />
        <asp:Button ID="btnMora" runat="server" Text="Moras" CssClass="btn btn-gold" OnClick="MostrarMoras" />
        <asp:Button ID="btnPolitica" runat="server" Text="Políticas" CssClass="btn btn-gold" OnClick="MostrarPoliticas" />
        <asp:Button ID="btnPoliticaReserva" runat="server" Text="Políticas Reserva" CssClass="btn btn-gold" OnClick="MostrarReservas" />
        <asp:Button ID="btnPoliticaPrestamo" runat="server" Text="Políticas Préstamo" CssClass="btn btn-gold" OnClick="MostrarPrestamos" />
    </div>
    <div class="container">

        <!-- Sección UNIVERSIDADES -->
        <div id="TablaUniversidades" runat="server" visible="true">
            <div class="card mb-10">
                <div class="card-header">
                    <h2>UNIVERSIDADES</h2>
                </div>
                <div class="card-body">
                    <!-- BOTÓN DE INGRESAR REGISTRO Y GRIDVIEW -->
                    <contenttemplate>
                        <!-- <asp:Button ID="btnAbrirModalUniversidad" CssClass="btn btn-success mb-3" runat="server" Text="Ingresar Universidad" OnClick="btnAbrirModalUniversidad_Click" /> >>

                        <!-- GRIDVIEW DE DATOS -->
                        <!-- <asp:BoundField DataField="idUniversidad" HeaderText="ID" /> esto estaba antes como fila-->
                        <div style="overflow-x: auto;">
                            <asp:GridView ID="GV_Universidades" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False">

                                <columns>

                                    <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                                    <asp:BoundField DataField="direccion" HeaderText="Dirección" />
                                    <asp:BoundField DataField="correoPersonalAdministrativo" HeaderText="Correo Administrativo" />
                                    <asp:BoundField DataField="extensionDominioCorreo" HeaderText="Extensión" />
                                    <asp:BoundField DataField="PoliticaRegular.etiqueta" HeaderText="Política Regular" />
                                    <asp:BoundField DataField="consorcioPerteneciente.CIF" HeaderText="Consorcio CIF" />
                                    <asp:TemplateField HeaderText="Acciones">
                                        <itemtemplate>
                                            <asp:LinkButton ID="lbEditarUniversidad" runat="server" CommandArgument='<%# Eval("idUniversidad") %>' OnClick="lbEditarUniversidad_Click" CssClass="btn btn-primary btn-sm">
                                                Editar
                                            </asp:LinkButton>
                                        </itemtemplate>
                                    </asp:TemplateField>
                                </columns>
                            </asp:GridView>
                        </div>

                        <!-- Modal para Insertar/Editar Universidad -->
                        <div class="modal fade" id="modalUniversidad" tabindex="-1" role="dialog" aria-labelledby="modalUniversidadLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 id="modalUniversidadLabel" runat="server" class="modal-title">Universidad</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- Campos del formulario -->
                                        <div class="form-group">
                                            <asp:Label runat="server" Text="Nombre:" AssociatedControlID="txtUniversidadNombre" />
                                            <asp:TextBox ID="txtUniversidadNombre" runat="server" CssClass="form-control"></asp:TextBox>
                                        </div>

                                        <div class="form-group">
                                            <asp:Label runat="server" Text="Dirección:" AssociatedControlID="txtUniversidadDireccion" />
                                            <asp:TextBox ID="txtUniversidadDireccion" runat="server" CssClass="form-control"></asp:TextBox>
                                        </div>

                                        <div class="form-group">
                                            <asp:Label runat="server" Text="Correo Administrativo:" AssociatedControlID="txtUniversidadCorreo" />
                                            <asp:TextBox ID="txtUniversidadCorreo" runat="server" CssClass="form-control"></asp:TextBox>
                                        </div>

                                        <div class="form-group">
                                            <asp:Label runat="server" Text="Extensión de Dominio de Correo:" AssociatedControlID="txtUniversidadExtension" />
                                            <asp:TextBox ID="txtUniversidadExtension" runat="server" CssClass="form-control"></asp:TextBox>
                                        </div>

                                        <div class="form-group">
                                            <asp:Label runat="server" Text="Política Regular:" AssociatedControlID="ddlUniversidadPoliticas" />
                                            <asp:DropDownList ID="ddlUniversidadPoliticas" runat="server" CssClass="form-select"></asp:DropDownList>
                                        </div>

                                        <div class="form-group">
                                            <asp:Label runat="server" Text="Consorcio:" AssociatedControlID="ddlUniversidadConsorcios" />
                                            <asp:DropDownList ID="ddlUniversidadConsorcios" runat="server" CssClass="form-select"></asp:DropDownList>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <asp:Button ID="btnCancelarUniversidad" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelarUniversidad_Click" />
                                        <asp:Button ID="btnGuardarUniversidad" CssClass="btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardarUniversidad_Click" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </contenttemplate>
                </div>

                <!-- JavaScript para manejar el modal -->
                <script type="text/javascript">
                    function showModalUniversidad() {
                        $('#modalUniversidad').modal('show');
                    }

                    function hideModalUniversidad() {
                        $('#modalUniversidad').modal('hide');
                    }
                </script>
            </div>
        </div>
        <div id="TablaBiblioteca" runat="server" visible="true">
            <!-- Sección BIBLIOTECAS -->
            <div class="card mb-4">
                <div class="card-header">
                    <h2>BIBLIOTECAS</h2>
                </div>
                <div class="card-body">
                    <!-- BOTÓN DE INGRESAR REGISTRO Y GRIDVIEW -->
                    <contenttemplate>
                        <asp:Button ID="btnAbrirModalBiblioteca" CssClass="btn btn-success mb-3" runat="server" Text="Ingresar Biblioteca" OnClick="btnAbrirModalBiblioteca_Click" />

                        <!-- GRIDVIEW DE DATOS -->
                        <!--<asp:BoundField DataField="idBiblioteca" HeaderText="ID" ReadOnly="True" />-->
                        <div style="overflow-x: auto;">
                            <asp:GridView ID="GV_Bibliotecas" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False" DataKeyNames="idBiblioteca">
                                <columns>

                                    <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                                    <asp:BoundField DataField="ubicacion" HeaderText="Ubicación" />
                                    <asp:TemplateField HeaderText="Universidad Asociada">
                                        <itemtemplate>
                                            <%# Eval("universidadAsociada.nombre") %>
                                        </itemtemplate>
                                    </asp:TemplateField>
                                    <asp:TemplateField HeaderText="Acciones">
                                        <itemtemplate>
                                            <asp:LinkButton ID="lbEditarBiblioteca" runat="server" CommandArgument='<%# Eval("idBiblioteca") %>' OnClick="lbEditarBiblioteca_Click" CssClass="btn btn-primary btn-sm">
                                                Editar
                                            </asp:LinkButton>
                                            <asp:LinkButton ID="lbEliminarBiblioteca" runat="server" CommandArgument='<%# Eval("idBiblioteca") %>' OnClick="lbEliminarBiblioteca_Click" OnClientClick="return confirm('¿Está seguro de que desea eliminar esta biblioteca?');" CssClass="btn btn-danger btn-sm">
                                                Eliminar
                                            </asp:LinkButton>
                                        </itemtemplate>
                                    </asp:TemplateField>
                                </columns>
                            </asp:GridView>
                        </div>
                    </contenttemplate>
                </div>
            </div>
            <!-- Modal para Insertar/Editar Biblioteca -->
            <div class="modal fade" id="modalBiblioteca" tabindex="-1" role="dialog" aria-labelledby="modalBibliotecaLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 id="modalBibliotecaLabel" runat="server" class="modal-title">Biblioteca</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Campos del formulario -->
                            <div class="form-group">
                                <asp:Label runat="server" Text="Nombre:" AssociatedControlID="txtBibliotecaNombre" />
                                <asp:TextBox ID="txtBibliotecaNombre" runat="server" CssClass="form-control"></asp:TextBox>
                            </div>

                            <div class="form-group">
                                <asp:Label runat="server" Text="Ubicación:" AssociatedControlID="txtBibliotecaUbicacion" />
                                <asp:TextBox ID="txtBibliotecaUbicacion" runat="server" CssClass="form-control"></asp:TextBox>
                            </div>

                            <div class="form-group">
                                <asp:Label runat="server" Text="Universidad Asociada:" AssociatedControlID="ddlBibliotecaUniversidad" />
                                <asp:DropDownList ID="ddlBibliotecaUniversidad" runat="server" CssClass="form-select"></asp:DropDownList>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <asp:Button ID="btnCancelarBiblioteca" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelarBiblioteca_Click" />
                            <asp:Button ID="btnGuardarBiblioteca" CssClass="btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardarBiblioteca_Click" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- JavaScript para manejar el modal (Bootstrap 5) -->
        <script type="text/javascript">

            function showModalBiblioteca() {
                $('#modalBiblioteca').modal('show');
            }
            function hideModalBiblioteca() {
                $('#modalBiblioteca').modal('hide');
            }
        </script>

        <!-- Sección MORAS -->
        <div id="TablaMoras" runat="server" visible="true">
            <div class="card mb-4">
                <div class="card-header">
                    <h2>MORAS</h2>
                </div>
                <div class="card-body">
                    <!-- BOTÓN DE INGRESAR REGISTRO Y GRIDVIEW -->
                    <asp:Button ID="btnAbrirModalMora" CssClass="btn btn-success mb-3" runat="server" Text="Ingresar Mora" OnClick="btnAbrirModalMora_Click" />

                    <!-- GRIDVIEW DE DATOS -->
                    <div style="overflow-x: auto;">
                        <asp:GridView ID="GV_Moras" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False" DataKeyNames="idMora">
                            <columns>

                                <asp:BoundField DataField="monto" HeaderText="Monto" DataFormatString="{0:C}" />
                                <asp:TemplateField HeaderText="Acciones">
                                    <itemtemplate>
                                        <asp:LinkButton ID="lbEditarMora" runat="server" CommandArgument='<%# Eval("idMora") %>' OnClick="lbEditarMora_Click" CssClass="btn btn-primary btn-sm">
                                            Editar
                                        </asp:LinkButton>
                                        <asp:LinkButton ID="lbEliminarMora" runat="server" CommandArgument='<%# Eval("idMora") %>' OnClick="lbEliminarMora_Click" OnClientClick="return confirm('¿Está seguro de que desea eliminar esta mora?');" CssClass="btn btn-danger btn-sm">
                                            Eliminar
                                        </asp:LinkButton>
                                    </itemtemplate>
                                </asp:TemplateField>
                            </columns>
                        </asp:GridView>
                    </div>

                    <!-- Modal para Insertar/Editar Mora -->
                    <div class="modal fade" id="modalMora" tabindex="-1" role="dialog" aria-labelledby="modalMoraLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="modalMoraLabel" runat="server" class="modal-title">Mora</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- Campos del formulario -->
                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Monto:" AssociatedControlID="txtMontoMora" />
                                        <asp:TextBox ID="txtMontoMora" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <asp:Button ID="btnCancelarMora" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelarMora_Click" />
                                    <asp:Button ID="btnGuardarMora" CssClass="btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardarMora_Click" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- JavaScript para manejar el modal -->
                <script type="text/javascript">
                    function showModalMora() {
                        $('#modalMora').modal('show');
                    }

                    function hideModalMora() {
                        $('#modalMora').modal('hide');
                    }
                </script>
            </div>
        </div>

        <!-- Sección POLÍTICAS -->
        <div id="TablaPoliticas" runat="server" visible="true">
            <div class="card mb-4">
                <div class="card-header">
                    <h2>POLÍTICAS</h2>
                </div>
                <div class="card-body">
                    <!-- BOTÓN DE INGRESAR REGISTRO Y GRIDVIEW -->
                    <contenttemplate>
                        <asp:Button ID="btnAbrirModalPolitica" CssClass="btn btn-success mb-3" runat="server" Text="Ingresar Política" OnClick="btnAbrirModalPolitica_Click" />

                        <!--<asp:BoundField DataField="idPolitica" HeaderText="ID" ReadOnly="True" /> -->
                        <!-- GRIDVIEW DE DATOS -->
                        <div style="overflow-x: auto;">
                            <asp:GridView ID="GV_Politicas" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False" DataKeyNames="idPolitica">
                                <columns>

                                    <asp:BoundField DataField="etiqueta" HeaderText="Etiqueta" />
                                    <asp:BoundField DataField="politicasPorPrestamo.idPoliticaPrestamo" HeaderText="Código de la política de préstamos" />
                                    <asp:BoundField DataField="politicasPorReserva.idPoliticaReserva" HeaderText="Código de la política por reservas" />
                                    <asp:TemplateField HeaderText="Acciones">
                                        <itemtemplate>
                                            <asp:LinkButton ID="lbEditarPolitica" runat="server" CommandArgument='<%# Eval("idPolitica") %>' OnClick="lbEditarPolitica_Click" CssClass="btn btn-primary btn-sm">
                                                Editar
                                            </asp:LinkButton>
                                            <asp:LinkButton ID="lbEliminarPolitica" runat="server" CommandArgument='<%# Eval("idPolitica") %>' OnClick="lbEliminarPolitica_Click" OnClientClick="return confirm('¿Está seguro de que desea eliminar esta política?');" CssClass="btn btn-danger btn-sm">
                                                Eliminar
                                            </asp:LinkButton>
                                        </itemtemplate>
                                    </asp:TemplateField>
                                </columns>
                            </asp:GridView>
                        </div>
                    </contenttemplate>

                    <!-- Modal para Insertar/Editar Política -->
                    <div class="modal fade" id="modalPolitica" tabindex="-1" role="dialog" aria-labelledby="modalPoliticaLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="modalPoliticaLabel" runat="server" class="modal-title">Política</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                                </div>
                                <div class="modal-body">
                                    <!-- Campos del formulario -->
                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Etiqueta:" AssociatedControlID="txtPoliticaEtiqueta" />
                                        <asp:TextBox ID="txtPoliticaEtiqueta" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Política por Préstamo:" AssociatedControlID="ddlPoliticasPrestamo" />
                                        <asp:DropDownList ID="ddlPoliticasPrestamo" runat="server" CssClass="form-select"></asp:DropDownList>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Política por Reserva:" AssociatedControlID="ddlPoliticasReserva" />
                                        <asp:DropDownList ID="ddlPoliticasReserva" runat="server" CssClass="form-select"></asp:DropDownList>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <asp:Button ID="btnCancelarPolitica" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelarPolitica_Click" />
                                    <asp:Button ID="btnGuardarPolitica" CssClass="btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardarPolitica_Click" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    function showModalPolitica() {
                        $('#modalPolitica').modal('show');
                    }
                    function hideModalPoliticaa() {
                        $('#modalPolitica').modal('hide');
                    }
                </script>
            </div>
        </div>

        <!-- Sección POLÍTICA DE RESERVAS -->
        <div id="TablaPoliticaReservas" runat="server" visible="true">
            <div class="card mb-4">
                <div class="card-header">
                    <h2>POLÍTICA DE RESERVAS</h2>
                </div>
                <div class="card-body">
                    <!-- BOTÓN DE INGRESAR REGISTRO Y GRIDVIEW -->
                    <asp:Button ID="btnAbrirModalPoliticaReserva" CssClass="btn btn-success mb-3" runat="server" Text="Ingresar Política de Reserva" OnClick="btnAbrirModalPoliticaReserva_Click" />

                    <!-- GRIDVIEW DE DATOS -->
                    <div style="overflow-x: auto;">
                        <asp:GridView ID="GV_PoliticasReserva" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False" DataKeyNames="idPoliticaReserva">
                            <columns>
                                <asp:BoundField DataField="idPoliticaReserva" HeaderText="Código de la política de reservas" ReadOnly="True" />
                                <asp:BoundField DataField="cantMaxHorasDeRecojo" HeaderText="Máximo Horas de Recogida" />
                                <asp:BoundField DataField="cantMaxReservasAgendadasPorUsuario" HeaderText="Máximo Reservas Agendadas por Usuario" />
                                <asp:TemplateField HeaderText="Acciones">
                                    <itemtemplate>
                                        <asp:LinkButton ID="lbEditarPoliticaReserva" runat="server" CommandArgument='<%# Eval("idPoliticaReserva") %>' OnClick="lbEditarPoliticaReserva_Click" CssClass="btn btn-primary btn-sm">
                                            Editar
                                        </asp:LinkButton>
                                        <asp:LinkButton ID="lbEliminarPoliticaReserva" runat="server" CommandArgument='<%# Eval("idPoliticaReserva") %>' OnClick="lbEliminarPoliticaReserva_Click" OnClientClick="return confirm('¿Está seguro de que desea eliminar esta política de reserva?');" CssClass="btn btn-danger btn-sm">
                                            Eliminar
                                        </asp:LinkButton>
                                    </itemtemplate>
                                </asp:TemplateField>
                            </columns>
                        </asp:GridView>
                    </div>

                    <!-- Modal para Insertar/Editar Política de Reserva -->
                    <div class="modal fade" id="modalPoliticaReserva" tabindex="-1" role="dialog" aria-labelledby="modalPoliticaReservaLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="modalPoliticaReservaLabel" runat="server" class="modal-title">Política de Reserva</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- Campos del formulario -->
                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Máximo Horas de Recogida:" AssociatedControlID="txtCantMaxHorasDeRecojo" />
                                        <asp:TextBox ID="txtCantMaxHorasDeRecojo" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Máximo Reservas Agendadas por Usuario:" AssociatedControlID="txtCantMaxReservasAgendadasPorUsuario" />
                                        <asp:TextBox ID="txtCantMaxReservasAgendadasPorUsuario" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <asp:Button ID="btnCancelarPoliticaReserva" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelarPoliticaReserva_Click" />
                                    <asp:Button ID="btnGuardarPoliticaReserva" CssClass="btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardarPoliticaReserva_Click" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- JavaScript para manejar el modal -->
                <script type="text/javascript">
                    function showModalPoliticaReserva() {
                        $('#modalPoliticaReserva').modal('show');
                    }

                    function hideModalPoliticaReserva() {
                        $('#modalPoliticaReserva').modal('hide');
                    }
                </script>
            </div>
        </div>
        <!-- Sección POLÍTICA DE PRÉSTAMO -->
        <div id="TablaPoliticaPrestamo" runat="server" visible="true">
            <div class="card mb-4">
                <div class="card-header">
                    <h2>POLÍTICA DE PRÉSTAMO</h2>
                </div>
                <div class="card-body">
                    <!-- BOTÓN DE INGRESAR REGISTRO Y GRIDVIEW -->
                    <asp:Button ID="btnAbrirModalPoliticaPrestamo" CssClass="btn btn-success mb-3" runat="server" Text="Ingresar Política de Préstamo" OnClick="btnAbrirModalPoliticaPrestamo_Click" />

                    <!-- GRIDVIEW DE DATOS -->
                    <div style="overflow-x: auto;">
                        <asp:GridView ID="GV_PoliticasPrestamo" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False" DataKeyNames="idPoliticaPrestamo">
                            <columns>
                                <asp:BoundField DataField="idPoliticaPrestamo" HeaderText="Código de la política de préstamos" ReadOnly="True" />
                                <asp:BoundField DataField="cantDiasDeAmpliacionRegular" HeaderText="Días de Ampliación Regular" />
                                <asp:BoundField DataField="cantDiasParaDarPorPerdidaUnaCopia" HeaderText="Días para Dar por Pérdida una Copia" />
                                <asp:BoundField DataField="cantDiasPrestamoRegular" HeaderText="Días de Préstamo Regular" />
                                <asp:BoundField DataField="cantDiasSinPrestamoPorAtraso" HeaderText="Días Sin Préstamo por Atraso" />
                                <asp:BoundField DataField="cantDiasSinPrestamoPorMalEstado" HeaderText="Días Sin Préstamo por Mal Estado" />
                                <asp:BoundField DataField="cantDiasSinPrestamoPorPerdido" HeaderText="Días Sin Préstamo por Perdido" />
                                <asp:BoundField DataField="cantMaxAmpliacionesPermitidasPorPrestamo" HeaderText="Máx. Ampliaciones por Préstamo" />
                                <asp:BoundField DataField="cantMaxAtrasosPorCiclo" HeaderText="Máx. Atrasos por Ciclo" />
                                <asp:BoundField DataField="cantMaxCopiasPorDevolverPorUsuario" HeaderText="Máx. Copias por Devolver por Usuario" />
                                <asp:BoundField DataField="cantMaxMalEstadoPorCiclo" HeaderText="Máx. Mal Estado por Ciclo" />
                                <asp:BoundField DataField="cantMaxPerdidasPorCiclo" HeaderText="Máx. Pérdidas por Ciclo" />
                                <asp:BoundField DataField="cargoPorMalEstado" HeaderText="Cargo por Mal Estado" DataFormatString="{0:C}" />
                                <asp:BoundField DataField="cargoPorPerdido" HeaderText="Cargo por Perdido" DataFormatString="{0:C}" />
                                <asp:BoundField DataField="moraAsociada.monto" HeaderText="Monto de la mora Asociada" DataFormatString="{0:C}" />
                                <asp:TemplateField HeaderText="Acciones">
                                    <itemtemplate>
                                        <asp:LinkButton ID="lbEditarPoliticaPrestamo" runat="server" CommandArgument='<%# Eval("idPoliticaPrestamo") %>' OnClick="lbEditarPoliticaPrestamo_Click" CssClass="btn btn-primary btn-sm">
                                            Editar
                                        </asp:LinkButton>
                                        <asp:LinkButton ID="lbEliminarPoliticaPrestamo" runat="server" CommandArgument='<%# Eval("idPoliticaPrestamo") %>' OnClick="lbEliminarPoliticaPrestamo_Click" OnClientClick="return confirm('¿Está seguro de que desea eliminar esta política de préstamo?');" CssClass="btn btn-danger btn-sm">
                                            Eliminar
                                        </asp:LinkButton>
                                    </itemtemplate>
                                </asp:TemplateField>
                            </columns>
                        </asp:GridView>
                    </div>

                    <!-- Modal para Insertar/Editar Política de Préstamo -->
                    <div class="modal fade" id="modalPoliticaPrestamo" tabindex="-1" role="dialog" aria-labelledby="modalPoliticaPrestamoLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 id="modalPoliticaPrestamoLabel" runat="server" class="modal-title">Política de Préstamo</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <!-- Campos del formulario -->
                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Días de Ampliación Regular:" AssociatedControlID="txtCantDiasDeAmpliacionRegular" />
                                        <asp:TextBox ID="txtCantDiasDeAmpliacionRegular" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Días para Dar por Pérdida una Copia:" AssociatedControlID="txtCantDiasParaDarPorPerdidaUnaCopia" />
                                        <asp:TextBox ID="txtCantDiasParaDarPorPerdidaUnaCopia" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Días de Préstamo Regular:" AssociatedControlID="txtCantDiasPrestamoRegular" />
                                        <asp:TextBox ID="txtCantDiasPrestamoRegular" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Días Sin Préstamo por Atraso:" AssociatedControlID="txtCantDiasSinPrestamoPorAtraso" />
                                        <asp:TextBox ID="txtCantDiasSinPrestamoPorAtraso" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Días Sin Préstamo por Mal Estado:" AssociatedControlID="txtCantDiasSinPrestamoPorMalEstado" />
                                        <asp:TextBox ID="txtCantDiasSinPrestamoPorMalEstado" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Días Sin Préstamo por Perdido:" AssociatedControlID="txtCantDiasSinPrestamoPorPerdido" />
                                        <asp:TextBox ID="txtCantDiasSinPrestamoPorPerdido" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Máx. Ampliaciones Permitidas por Préstamo:" AssociatedControlID="txtCantMaxAmpliacionesPermitidasPorPrestamo" />
                                        <asp:TextBox ID="txtCantMaxAmpliacionesPermitidasPorPrestamo" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Máx. Atrasos por Ciclo:" AssociatedControlID="txtCantMaxAtrasosPorCiclo" />
                                        <asp:TextBox ID="txtCantMaxAtrasosPorCiclo" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Máx. Copias por Devolver por Usuario:" AssociatedControlID="txtCantMaxCopiasPorDevolverPorUsuario" />
                                        <asp:TextBox ID="txtCantMaxCopiasPorDevolverPorUsuario" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Máx. Mal Estado por Ciclo:" AssociatedControlID="txtCantMaxMalEstadoPorCiclo" />
                                        <asp:TextBox ID="txtCantMaxMalEstadoPorCiclo" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Máx. Pérdidas por Ciclo:" AssociatedControlID="txtCantMaxPerdidasPorCiclo" />
                                        <asp:TextBox ID="txtCantMaxPerdidasPorCiclo" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Cargo por Mal Estado:" AssociatedControlID="txtCargoPorMalEstado" />
                                        <asp:TextBox ID="txtCargoPorMalEstado" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Cargo por Perdido:" AssociatedControlID="txtCargoPorPerdido" />
                                        <asp:TextBox ID="txtCargoPorPerdido" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>

                                    <div class="form-group">
                                        <asp:Label runat="server" Text="Mora Asociada:" AssociatedControlID="ddlMoraAsociada" />
                                        <asp:DropDownList ID="ddlMoraAsociada" runat="server" CssClass="form-control">
                                        </asp:DropDownList>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <asp:Button ID="btnCancelarPoliticaPrestamo" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelarPoliticaPrestamo_Click" />
                                    <asp:Button ID="btnGuardarPoliticaPrestamo" CssClass="btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardarPoliticaPrestamo_Click" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- JavaScript para manejar el modal -->
                <script type="text/javascript">
                    function showModalPoliticaPrestamo() {
                        $('#modalPoliticaPrestamo').modal('show');
                    }

                    function hideModalPoliticaPrestamo() {
                        $('#modalPoliticaPrestamo').modal('hide');
                    }
                </script>
            </div>


        </div>

    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
