<%@ Page Title="Detalle" Language="C#" MasterPageFile="~/MaestroMiembro.Master" AutoEventWireup="true" CodeBehind="DetallePublicacion.aspx.cs" Inherits="SoftBibliotecaWA.DetallePublicacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

    <div class="container mt-4">
        <h1>Detalle de la Publicación</h1>

        <div class="row">
            <div class="col-md-4">
                <asp:Image ID="imgPortada" runat="server" CssClass="img-fluid mb-3" Style="width: 100%; height: auto;" alt="Portada" />
            </div>
            <div class="col-md-8">
                <h3>
                    <asp:Label ID="lblTitulo" runat="server" Text="" CssClass="fw-bold"></asp:Label></h3>
                <p>
                    <asp:Label ID="lblDescripcion" runat="server" Text="" CssClass="lead"></asp:Label>
                </p>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Autor(es):</strong>
                        <asp:Label ID="lblAutores" runat="server" Text="" Style="white-space: pre-wrap;"></asp:Label></li>
                    <li class="list-group-item"><strong>Biblioteca:</strong>
                        <asp:Label ID="lblBiblioteca" runat="server" Text=""></asp:Label></li>
                    <li class="list-group-item">
                        <asp:Label ID="lblAcceso" runat="server" Text="" CssClass="fw-bold"></asp:Label>
                    </li>
                    <li class="list-group-item"><strong>Número de copias disponibles:</strong>
                        <asp:Label ID="lblNumCopias" runat="server" Text=""></asp:Label></li>
                    <li class="list-group-item"><strong>URL:</strong>
                        <asp:HyperLink ID="hlURL" runat="server" Text="Ir a la publicación" NavigateUrl="" Target="_blank"></asp:HyperLink></li>
                </ul>
            </div>
        </div>

        <div class="d-flex align-items-center mt-4">
            <asp:Button ID="btnRegresarCatalogo" runat="server" Text="Regresar" OnClick="btnRegresarCatalogo_Click" CssClass="btn btn-primary" />
            <asp:Button ID="btnSolicitarReserva" runat="server" Text="Reserva" CssClass="btn btn-secondary ms-2" OnClick="btnSolicitarReserva_Click" />
        </div>
        <!-- Contenedor para Campos Personalizados de LIBROS y TESIS  -->
        <div class="mt-4">
            <div class="card">
                <div class="card-header">
                    <h3>Información Adicional</h3>
                </div>
                <div class="card-body">
                    <asp:PlaceHolder ID="phCamposPersonalizados" runat="server"></asp:PlaceHolder>
                </div>
            </div>
        </div>

        <!-- MODAL CONFIRMAR RESERVA -->
        <div class="modal fade" id="reservaModal" tabindex="-1" aria-labelledby="reservaModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-custom-margin">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="reservaModalLabel">Confirmar Reserva</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <h5>Detalles de la Publicación</h5>
                        <p>¿Está seguro de que desea solicitar la reserva de esta publicación?</p>
                        <ul class="list-group">
                            <li class="list-group-item"><strong>Título:</strong>
                                <asp:Label ID="lblTituloModal" runat="server" Text=""></asp:Label>
                            </li>
                            <li class="list-group-item"><strong>Descripción:</strong>
                                <asp:Label ID="lblDescripcionModal" runat="server" Text=""></asp:Label>
                            </li>
                            <li class="list-group-item"><strong>Autor(es):</strong>
                                <asp:Label ID="lblAutoresModal" runat="server" Text=""></asp:Label>
                            </li>
                            <li class="list-group-item"><strong>Biblioteca:</strong>
                                <asp:Label ID="lblBibliotecaModal" runat="server" Text=""></asp:Label>
                            </li>
                            <li class="list-group-item"><strong>Número de copias disponibles:</strong>
                                <asp:Label ID="lblNumCopiasModal" runat="server" Text=""></asp:Label>
                            </li>
                           
                            <li class="list-group-item"><strong>Código de la copia disponible:</strong>
                                <asp:Label ID="lblCodigoCopia" runat="server" Text=""></asp:Label>
                            </li>
                            <li class="list-group-item"><strong>Fecha de recojo:</strong>
                                <asp:Label ID="lblFechaRecojo" runat="server" Text=""></asp:Label>
                            </li>
                            <li class="list-group-item"><strong>Hora máxima de recojo:</strong>
                                <asp:Label ID="lblHoraMaxRecojo" runat="server" Text=""></asp:Label>
                            </li>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <asp:Button ID="btnConfirmarReserva" runat="server" Text="Confirmar Reserva" CssClass="btn btn-primary" OnClick="btnConfirmarReserva_Click" />
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- MODAL NO CALIFICA PARA RESERVA -->
        <div class="modal fade" id="noCalificaModal" tabindex="-1" aria-labelledby="noCalificaModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="noCalificaModalLabel">Reserva No Permitida</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        Usted no califica para realizar esta reserva.
                   
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Regresar</button>
                    </div>
                </div>
            </div>
        </div>


        <!--estado reserva modal-->
        <div class="modal fade" id="estadoReservaModal" tabindex="-1" aria-labelledby="estadoReservaModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="estadoReservaModalLabel">Resultado de la Reserva</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <asp:Label ID="lblEstadoReserva" runat="server" CssClass="fw-bold"></asp:Label>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Libros Recomendados -->
        <style>
            /* Estilo personalizado para las imágenes recomendadas */
            .img-recomendado {
                max-height: 150px; /* Limita la altura máxima */
                object-fit: contain; /* Asegura que la imagen completa sea visible */
                padding: 10px; /* Opcional: espacio alrededor de la imagen */
            }

            /* Reducir el padding del card-body para hacer la tarjeta más compacta */
            .card-body {
                padding: 10px;
            }

            /* Eliminar la altura mínima de la tarjeta para evitar que sea demasiado grande */
            .card {
                /* min-height: 300px; */ /* Comentado para eliminar la altura mínima */
                /* Opcional: ajustar el ancho máximo si es necesario */
                /* max-width: 18rem; */
            }

            /* Ajustar el tamaño del texto de la descripción */
            .card-text {
                font-size: 0.9rem;
            }
        </style>
        <!-- Libros y Tesis Recomendados -->
        <div class="mt-4">
            <h3 class="text-center">Publicaciones Recomendadas</h3>
            <div class="row justify-content-center">
                <asp:Repeater ID="rptLibrosRecomendados" runat="server">
                    <ItemTemplate>
                        <div class="col-md-4 mb-4 d-flex justify-content-center">
                            <div class="card">
                                <asp:Image
                                    ID="imgRecomendado"
                                    runat="server"
                                    CssClass="card-img-top img-recomendado"
                                    ImageUrl='<%# Eval("PortadaUrl") %>'
                                    Alt="Portada del libro" />
                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title mb-2">
                                        <asp:HyperLink
                                            ID="hlTituloRecomendado"
                                            runat="server"
                                            NavigateUrl='<%# "DetallePublicacion.aspx?id=" + Eval("idPublicacion") + "&tipo=" + Eval("TipoPublicacion").ToString().ToLower() %>'
                                            CssClass="text-dark text-decoration-none fw-bold">
                                    <%# Eval("titulo") %>
                                </asp:HyperLink>
                                    </h5>
                                    <p class="card-text mt-auto"><strong>Descripción:</strong> <%# Eval("descripcion") %></p>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
        </div>
    </div>
</asp:Content>
