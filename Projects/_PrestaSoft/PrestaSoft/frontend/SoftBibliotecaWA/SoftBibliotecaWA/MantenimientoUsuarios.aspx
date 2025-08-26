<%@ Page Title="Usuarios" Language="C#" MasterPageFile="~/MaestroAdministrador.Master" AutoEventWireup="true" CodeBehind="MantenimientoUsuarios.aspx.cs" Inherits="SoftBibliotecaWA.MantenimientoUsuarios" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <!-- Si necesitas referencias adicionales, agrégalas aquí -->
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
     <!-- Sección de Búsqueda -->
    <div class="container">
        <div class="mb-4">
            <h4>Buscar Usuarios</h4>
            <div class="row g-3">
                <!-- Buscar por Nombre -->
                <div class="col-md-4">
                    <label for="txtBuscarPorNombre" class="form-label">Buscar por Nombre</label>
                    <asp:TextBox ID="txtBuscarPorNombre" runat="server" CssClass="form-control" Placeholder="Ingrese nombre"></asp:TextBox>
                </div>
                
                <!-- Buscar por Apellido -->
                <div class="col-md-4">
                    <label for="txtBuscarPorApellido" class="form-label">Buscar por Apellido</label>
                    <asp:TextBox ID="txtBuscarPorApellido" runat="server" CssClass="form-control" Placeholder="Ingrese apellido"></asp:TextBox>
                </div>
                
                <!-- Buscar por Username -->
                <div class="col-md-4">
                    <label for="txtBuscarPorUsername" class="form-label">Buscar por Username</label>
                    <asp:TextBox ID="txtBuscarPorUsername" runat="server" CssClass="form-control" Placeholder="Ingrese username"></asp:TextBox>
                </div>
            </div>
            <div class="mt-3">
                <asp:Button ID="btnBuscarUsuarios" runat="server" CssClass="btn btn-primary" Text="Buscar" OnClick="btnBuscarUsuarios_Click" />
                <asp:Button ID="btnLimpiarBusqueda" runat="server" CssClass="btn btn-secondary ms-2" Text="Limpiar" OnClick="btnLimpiarBusqueda_Click" />
            </div>
        </div>
        <h2>Gestión de Usuarios</h2>

        <!-- Tabla de usuarios -->
        <asp:GridView ID="gvUsuarios" runat="server" AutoGenerateColumns="False" CssClass="table table-bordered"
            AllowPaging="true" PageSize="10" OnPageIndexChanging="gvUsuarios_PageIndexChanging" OnRowDataBound="gvUsuarios_RowDataBound">
            <Columns>
                <asp:BoundField DataField="idUsuario" HeaderText="Código de usuario" />
                <asp:BoundField DataField="nombres" HeaderText="Nombres" />
                <asp:BoundField DataField="apellidos" HeaderText="Apellidos" />
                <asp:BoundField DataField="correoInstitucional" HeaderText="Correo Institucional" />
                <asp:BoundField DataField="nombreUsuario" HeaderText="Username" />
                <asp:BoundField DataField="fechaRegistro" HeaderText="Fecha de Registro" DataFormatString="{0:dd/MM/yyyy}" />
                <asp:BoundField DataField="tipoUsuario" HeaderText="Tipo de Usuario" />

                <asp:TemplateField HeaderText="Universidades">
                    <ItemTemplate>
                        <asp:Label ID="lblUniversidades" runat="server"></asp:Label>
                        <itemstyle width="300px" />
                    </ItemTemplate>
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Acciones">
                    <ItemTemplate>
                        <asp:LinkButton ID="lbEditarUsuario" runat="server" CommandArgument='<%# Eval("idUsuario") %>' OnClick="lbEditarUsuario_Click" CssClass="btn btn-primary btn-sm">
                            Editar
                        </asp:LinkButton>

                        <asp:LinkButton ID="lbEliminarUsuario" runat="server" CommandArgument='<%# Eval("idUsuario") %>' OnClick="lbEliminarUsuario_Click" OnClientClick="return confirm('¿Está seguro de que desea eliminar este usuario?');" CssClass="btn btn-danger btn-sm">
                            Eliminar
                        </asp:LinkButton>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
        <asp:Label ID="lblCantidadUsuarios" runat="server" CssClass="mt-2"></asp:Label>
    </div>

    <!-- Modal para editar usuario -->
    <div class="modal fade" id="modalUsuario" tabindex="-1" role="dialog" aria-labelledby="modalUsuarioLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="modalUsuarioLabel" class="modal-title">Editar Usuario</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <asp:Label runat="server" Text="Nombre:" AssociatedControlID="txtNombreUsuario" />
                        <asp:TextBox ID="txtNombreUsuario" runat="server" CssClass="form-control" ReadOnly="true"></asp:TextBox>
                    </div>

                    <div class="form-group">
                        <asp:Label runat="server" Text="Apellidos:" AssociatedControlID="txtApellidosUsuario" />
                        <asp:TextBox ID="txtApellidosUsuario" runat="server" CssClass="form-control" ReadOnly="true"></asp:TextBox>
                    </div>

                    <div class="form-group">
                        <asp:Label runat="server" Text="Correo Institucional:" AssociatedControlID="txtCorreoUsuario" />
                        <asp:TextBox ID="txtCorreoUsuario" runat="server" CssClass="form-control" ReadOnly="true"></asp:TextBox>
                    </div>

                    <div class="form-group">
                        <asp:Label runat="server" Text="Username:" AssociatedControlID="txtUsername" />
                        <asp:TextBox ID="txtUsername" runat="server" CssClass="form-control" ReadOnly="true"></asp:TextBox>
                    </div>

                    <div class="form-group">
                        <asp:Label runat="server" Text="Tipo de Usuario:" AssociatedControlID="ddlTipoUsuario" />
                        <asp:DropDownList ID="ddlTipoUsuario" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Miembro" Value="MIEMBRO" />
                            <asp:ListItem Text="Bibliotecario" Value="BIBLIOTECARIO" />
                            <asp:ListItem Text="Programador" Value="PROGRAMADOR" />
                            <asp:ListItem Text="Inactivo" Value="INACTIVO" />
                        </asp:DropDownList>
                    </div>

                    <div class="form-group">
                        <asp:Label runat="server" Text="Universidades Asociadas:" />
                        <asp:CheckBoxList ID="cblUniversidades" runat="server" CssClass="form-check">
                        </asp:CheckBoxList>
                    </div>

                </div>
                <div class="modal-footer">
                    <asp:Button ID="btnCancelarUsuario" CssClass="btn btn-secondary" runat="server" Text="Cancelar" OnClick="btnCancelarUsuario_Click" />
                    <asp:Button ID="btnGuardarUsuario" CssClass="btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardarUsuario_Click" />
                </div>
            </div>
        </div>
    </div>

    <!-- JavaScript para manejar el modal -->
    <script type="text/javascript">
        function showModalUsuario() {
            $('#modalUsuario').modal('show');
        }

        function hideModalUsuario() {
            $('#modalUsuario').modal('hide');
        }
    </script>

</asp:Content>
