<%@ Page Title="" Language="C#" MasterPageFile="~/MaestroBibliotecario.Master" AutoEventWireup="true" CodeBehind="RegistrarDevolucion.aspx.cs" Inherits="SoftBibliotecaWA.RegistrarDevolucion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="w-100 py-3 px-3" style="background-color: #F0F8FF; color: #333;">
        <h5 class="mb-0 fw-bold fs-3 text-center">Registrar devolución</h5>
    </div>

    <div id="contenido" class="contenidoNormal mx-3" style="overflow-x: hidden; padding-top: 30px;">
        <div class="container d-flex justify-content-center align-items-center" style="min-height: 70vh;">
            <div class="row gy-4">
                <!-- Card Izquierdo -->
                <div class="col-lg-6 d-flex justify-content-end">
                    <div class="card shadow-lg border-0" style="width: 500px; border-radius: 15px;">
                        <div class="card-body p-4">
                            <h5 class="card-title text-center text-primary">Código de la copia ejemplar</h5>
                            <div class="input-group my-3">
                                <asp:TextBox ID="txtCodigoCopia" runat="server" CssClass="form-control"
                                    Placeholder="Ingrese el código de la copia"></asp:TextBox>
                                <asp:Button ID="btnConsultar" runat="server" CssClass="btn btn-primary" Text="Consultar" OnClick="btnConsultar_Click" />
                            </div>


                            <div class="text-center my-4">
                                <asp:Image ID="imgPortada" runat="server" CssClass="img-thumbnail shadow-sm" Width="200px" Visible="false" />
                            </div>
                            <div>
                                <asp:Label ID="lblIdPublicacion" CssClass="form-label fw-bold" runat="server" Text="" Visible="false"></asp:Label><br />
                                <asp:Label ID="lblTituloPublicacion" CssClass="form-label text-muted" runat="server" Text="" Visible="false"></asp:Label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Card Derecho -->
                <div class="col-lg-6 d-flex justify-content-start">
                    <div class="card shadow-lg border-0" style="max-width: 400px; border-radius: 15px;">
                        <div class="card-body p-4">
                            <h5 class="card-title text-center text-success">Miembro Asociado</h5>
                            <div class="mb-4">
                                <label for="nombre" class="form-label">Nombre</label>
                                <asp:TextBox ID="nombre" runat="server" CssClass="form-control bg-light text-muted" ReadOnly="true" />
                            </div>
                            <div class="mb-4" id="formOpciones" runat="server" visible="false">
                                <label for="opciones" class="form-label">Estado de la copia</label>
                                <asp:DropDownList ID="opciones" runat="server" CssClass="form-select">
                                    <asp:ListItem Text="Óptimo" Value="1"></asp:ListItem>
                                    <asp:ListItem Text="Mal Estado" Value="2"></asp:ListItem>
                                </asp:DropDownList>
                            </div>
                        </div>
                        <div class="card-footer text-center bg-light border-0">
                            <asp:Button ID="btnRegistrar" runat="server" CssClass="btn btn-success px-4 py-2" Text="Confirmar devolución" OnClick="btnConfirmarDevolucion_Click" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
