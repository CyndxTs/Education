<%@ Page Title="Gestion" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="GestionPublicacion.aspx.cs" Inherits="SoftBibliotecaWA.GestionPublicacion" %>

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
                <asp:Image ID="imgPortada" runat="server" CssClass="img-fluid mb-3" style="width: 100%; height: auto;" alt="Portada" />
            </div>
            <div class="col-md-8">
                <h3><asp:Label ID="lblTitulo" runat="server" Text="" CssClass="fw-bold"></asp:Label></h3>
                <p><asp:Label ID="lblDescripcion" runat="server" Text="" CssClass="lead"></asp:Label></p>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Autor(es):</strong> <asp:Label ID="lblAutores" runat="server" Text="" Style="white-space: pre-wrap;"></asp:Label></li>
                    <li class="list-group-item"><strong>Biblioteca:</strong> <asp:Label ID="lblBiblioteca" runat="server" Text=""></asp:Label></li>
                    <li class="list-group-item">
                        <asp:Label ID="lblAcceso" runat="server" Text="" CssClass="fw-bold" ></asp:Label>
                    </li>
                    <li class="list-group-item"><strong>Número de copias disponibles:</strong> <asp:Label ID="lblNumCopias" runat="server" Text=""></asp:Label></li>
                    <li class="list-group-item"><strong>URL:</strong> <asp:HyperLink ID="hlURL" runat="server" Text="Ir a la publicación" NavigateUrl="" Target="_blank"></asp:HyperLink></li>
                </ul>
            </div>
        </div>

        <div class="d-flex justify-content-between mt-4">
            <asp:Button ID="btnRegresar" runat="server" Text="Regresar al Catálogo" OnClick="btnRegresar_Click" CssClass="btn btn-secondary" />
            <asp:Button ID="btnModificar" runat="server" Text="Modificar" OnClick="btnModificar_Click" CssClass="btn btn-primary" />
        </div>

        <!-- Contenedor para Campos Personalizados de LIBROS y TESIS  -->
        <div class="mt-4">
            <div class="card">
                <div class="card-header"  >
                    <h3>Información Adicional</h3>
                </div>
                <div class="card-body">
                    <asp:PlaceHolder ID="phCamposPersonalizados" runat="server"></asp:PlaceHolder>
                </div>
            </div>
        </div>

    </div>

    <script type="text/javascript">
        function showModal() {
            var modal = new bootstrap.Modal(document.getElementById('editarModal'));
            modal.show();
        }
    </script>
</asp:Content>
