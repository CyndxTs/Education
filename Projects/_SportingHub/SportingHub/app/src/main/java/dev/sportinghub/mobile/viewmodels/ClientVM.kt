package dev.sportinghub.mobile.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.accounts.Account
import dev.sportinghub.mobile.model.accounts.AccountType
import dev.sportinghub.mobile.model.accounts.AdminAccount
import dev.sportinghub.mobile.model.accounts.BrandAccount
import dev.sportinghub.mobile.model.accounts.PersonalAccount
import dev.sportinghub.mobile.model.posts.BagItem
import dev.sportinghub.mobile.model.posts.Post
import dev.sportinghub.mobile.model.posts.PostStatus
import dev.sportinghub.mobile.model.posts.PostType
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.model.posts.PublicationDetailUiModel
import dev.sportinghub.mobile.model.posts.PublicationPost
import dev.sportinghub.mobile.model.posts.Variant
import dev.sportinghub.mobile.model.posts.VariantColor
import dev.sportinghub.mobile.model.posts.VariantMedia
import dev.sportinghub.mobile.model.posts.VariantSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ClientVM @Inject constructor(
    private val accountUseCase: AccountUseCase,
    private val orderUseCase: OrderUseCase,
    private val postUseCase: PostUseCase
): ViewModel() {
    private var client: Account? = null

    suspend fun loadDefaultData(context: Context) {
        val prefs = context.getSharedPreferences("init_data", Context.MODE_PRIVATE)
        val isLoaded = prefs.getBoolean("loaded", false)
        if (!isLoaded) {
            loadAccounts()
            loadPublicationPosts()
            loadVariantSizes()
            loadVariantColors()
            loadVariantMedia()
            loadVariants()
            prefs.edit().putBoolean("loaded", true).apply()
        }
    }

    private suspend fun loadAccounts() {
        accountUseCase.insert(PersonalAccount(email = "personal@SH.com",nickname = "personal123",password = "12345678", firstName = "Personal", lastName = "Number One", birthDate = SH_Formatter.toValidDate("1999-09-09"), gender = "M"))
        accountUseCase.insert(PersonalAccount(email = "cdxts.v1@gmail.com",nickname = "cyndx",password = "12345678", firstName = "Candy Angel", lastName = "Hyper Nova", birthDate = SH_Formatter.toValidDate("2004-06-12"), gender = "M"))
        accountUseCase.insert(BrandAccount(email = "brand@SH.com", nickname = "brand123", password = "12345678",businessName = "BRAND 123 S.A.C.", country = "PERU",taxNumber = "20663459331"))
        accountUseCase.insert(AdminAccount(email = "admin@SH.com", nickname = "admin123", password = "12345678", phoneNumber = "+51999999999"))
    }

    private suspend fun loadPublicationPosts() {
        postUseCase.insert(PublicationPost(publishedInstant = LocalDateTime.of(2025, 7, 15, 10, 5, 20), status = PostStatus.PUBLISHED, title = "Zapatillas para alto rendimiento", description = "Exploramos las mejores opciones para corredores profesionales.", authorType = AccountType.BRAND, authorId = 1))
        postUseCase.insert(PublicationPost(publishedInstant = LocalDateTime.of(2025, 7, 15, 16, 30, 10), status = PostStatus.PUBLISHED, title = "Guantes de entrenamiento recomendados", description = "Probamos distintos modelos para levantamiento y crossfit.", authorType = AccountType.PERSONAL, authorId = 1))
        postUseCase.insert(PublicationPost(publishedInstant = LocalDateTime.of(2025, 7, 16, 9, 45, 0), status = PostStatus.PUBLISHED, title = "Reseña de la nueva línea de ropa térmica", description = "¿Vale la pena para entrenamientos en invierno?", authorType = AccountType.PERSONAL, authorId = 2))
        postUseCase.insert(PublicationPost(publishedInstant = LocalDateTime.of(2025, 7, 16, 18, 10, 35), status = PostStatus.PUBLISHED, title = "Comparativa de botellas deportivas", description = "Analizamos diseño, resistencia y facilidad de uso.", authorType = AccountType.BRAND, authorId = 1))
        postUseCase.insert(PublicationPost(publishedInstant = LocalDateTime.of(2025, 7, 17, 12, 55, 50), status = PostStatus.PUBLISHED, title = "Top 5 mochilas para entrenamiento diario", description = "Ligereza, espacio y durabilidad en un solo producto.", authorType = AccountType.PERSONAL, authorId = 2))
    }

    private suspend fun loadVariantSizes() {
        postUseCase.insert(VariantSize(name = "XS"))
        postUseCase.insert(VariantSize(name = "S"))
        postUseCase.insert(VariantSize(name = "M"))
        postUseCase.insert(VariantSize(name = "L"))
        postUseCase.insert(VariantSize(name = "XL"))
        postUseCase.insert(VariantSize(name = "XXL"))
        postUseCase.insert(VariantSize(name = "38"))
        postUseCase.insert(VariantSize(name = "40"))
        postUseCase.insert(VariantSize(name = "42"))
        postUseCase.insert(VariantSize(name = "Talla Única"))
    }

    private suspend fun loadVariantColors() {
        postUseCase.insert(VariantColor(name = "Rojo"))
        postUseCase.insert(VariantColor(name = "Azul"))
        postUseCase.insert(VariantColor(name = "Verde"))
        postUseCase.insert(VariantColor(name = "Negro"))
        postUseCase.insert(VariantColor(name = "Blanco"))
        postUseCase.insert(VariantColor(name = "Amarillo"))
        postUseCase.insert(VariantColor(name = "Gris"))
        postUseCase.insert(VariantColor(name = "Naranja"))
        postUseCase.insert(VariantColor(name = "Morado"))
        postUseCase.insert(VariantColor(name = "Rosado"))
    }

    private suspend  fun loadVariants() {
        postUseCase.insert(Variant(publicationId = 1, price = 10.99f, sizeId = 1, colorId = 1))
        postUseCase.insert(Variant(publicationId = 1, price = 12.50f, sizeId = 2, colorId = 1))
        postUseCase.insert(Variant(publicationId = 1, price = 10.99f, sizeId = 1, colorId = 2))
        postUseCase.insert(Variant(publicationId = 2, price = 25.00f, sizeId = 3, colorId = 1))
        postUseCase.insert(Variant(publicationId = 2, price = 22.75f, sizeId = 2, colorId = 2))
        postUseCase.insert(Variant(publicationId = 3, price = 35.99f, sizeId = 4, colorId = 4)) // L, Negro
        postUseCase.insert(Variant(publicationId = 3, price = 35.99f, sizeId = 3, colorId = 5)) // M, Blanco
        postUseCase.insert(Variant(publicationId = 3, price = 37.50f, sizeId = 5, colorId = 1)) // XL, Rojo
        postUseCase.insert(Variant(publicationId = 4, price = 15.00f, sizeId = null, colorId = 2)) // Azul
        postUseCase.insert(Variant(publicationId = 4, price = 15.00f, sizeId = null, colorId = 7)) // Gris
        postUseCase.insert(Variant(publicationId = 4, price = 17.25f, sizeId = null, colorId = 10)) // Rosado
        postUseCase.insert(Variant(publicationId = 5, price = 45.00f, sizeId = 10, colorId = 4)) // Talla Única, Negro
        postUseCase.insert(Variant(publicationId = 5, price = 46.50f, sizeId = 10, colorId = 6)) // Talla Única, Amarillo
        postUseCase.insert(Variant(publicationId = 5, price = 47.99f, sizeId = 10, colorId = 3)) // Talla Única, Verde
    }

    private suspend fun loadVariantMedia() {
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_1_img1.jpg", variantId = 1))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_1_img2.jpg", variantId = 1))
        postUseCase.insert(VariantMedia(position = 3, mediaUrl = "/posts/variant_1_img3.jpg", variantId = 1))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_2_img1.jpg", variantId = 2))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_2_img2.jpg", variantId = 2))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_3_img1.jpg", variantId = 3))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_4_img1.jpg", variantId = 4))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_4_img2.jpg", variantId = 4))
        postUseCase.insert(VariantMedia(position = 3, mediaUrl = "/posts/variant_4_img3.jpg", variantId = 4))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_5_img1.jpg", variantId = 5))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_5_img2.jpg", variantId = 5))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_6_img1.jpg", variantId = 6))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_7_img1.jpg", variantId = 7))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_7_img2.jpg", variantId = 7))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_8_img1.jpg", variantId = 8))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_8_img2.jpg", variantId = 8))
        postUseCase.insert(VariantMedia(position = 3, mediaUrl = "/posts/variant_8_img3.jpg", variantId = 8))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_9_img1.jpg", variantId = 9))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_10_img1.jpg", variantId = 10))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_10_img2.jpg", variantId = 10))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_11_img1.jpg", variantId = 11))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_12_img1.jpg", variantId = 12))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_12_img2.jpg", variantId = 12))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_13_img1.jpg", variantId = 13))
        postUseCase.insert(VariantMedia(position = 1, mediaUrl = "/posts/variant_14_img1.jpg", variantId = 14))
        postUseCase.insert(VariantMedia(position = 2, mediaUrl = "/posts/variant_14_img2.jpg", variantId = 14))
        postUseCase.insert(VariantMedia(position = 3, mediaUrl = "/posts/variant_14_img3.jpg", variantId = 14))
    }

    suspend fun signUp(accountType: String, email: String = "", nickname: String = "", password: String = "",
                       photoUrl: String? = null, bio: String? = null,firstName: String = "",lastName: String = "",
                       birthDate: String? = null,gender: String? = null, businessName: String = "", country: String = "",
                       taxNumber: String = "", phoneNumber: String = ""): Boolean {
        if (client != null) return false
        val newAccount = getAccountModel(
                accountType = SH_Formatter.toValidEnum(accountType, AccountType::class.java) ?: return false,
                email = email,
                nickname = nickname,
                password = password
            )
        accountUseCase.setGeneralFields(newAccount,photoUrl,bio)
        accountUseCase.setSpecificFields(newAccount,firstName,lastName, SH_Formatter.toValidDate(birthDate),gender,businessName,country,taxNumber,phoneNumber)
        client = accountUseCase.signUp(newAccount)
        return true
    }

    suspend fun signIn(email: String = "", nickname: String = "", password: String): Boolean {
        if (client != null) return false
        var accountModel: Account
        if(SH_Formatter.isValidCondition(email)) {
            accountModel = getAccountModel(accountType = AccountType.PERSONAL,email = email, password = password)
            client = accountUseCase.signIn(accountModel)
            if (client != null) return true
            accountModel = getAccountModel(accountType = AccountType.BRAND,email = email, password = password)
            client = accountUseCase.signIn(accountModel)
            if (client != null) return true
            accountModel = getAccountModel(accountType = AccountType.ADMIN,email = email, password = password)
            client = accountUseCase.signIn(accountModel)
            if (client != null) return true
        }
        if(SH_Formatter.isValidCondition(nickname)) {
            accountModel = getAccountModel(accountType = AccountType.PERSONAL,nickname = nickname, password = password)
            client = accountUseCase.signIn(accountModel)
            if (client != null) return true
            accountModel = getAccountModel(accountType = AccountType.BRAND,nickname = nickname, password = password)
            client = accountUseCase.signIn(accountModel)
            if (client != null) return true
            accountModel = getAccountModel(accountType = AccountType.ADMIN,nickname = nickname, password = password)
            client = accountUseCase.signIn(accountModel)
            if (client != null) return true
        }
        return false
    }

    suspend fun signOut(): Boolean {
        client?.let {
            client = accountUseCase.signOut(it)
            return true
        }
        return false
    }

    fun isValidPassword(password: String): Boolean {
        return when {
            password.length < 6 -> false
            else -> true
        }
    }

    suspend fun isFreeEmail(email: String): Boolean {
        return accountUseCase.isFreeEmail(email)
    }

    suspend fun isFreeNickname(nickname: String): Boolean {
        return accountUseCase.isFreeNickname(nickname)
    }

    suspend fun searchAccounts(email: String = "",nickname: String = "", accountStatus: String? = null, accountType: String? = null,
                               firstName: String = "", lastName: String = "", birthDate: String = "", gender: String = "",businessName: String = "",
                               country: String = "", taxNumber: String = "",phoneNumber: String = ""): MutableList<Account> {
        var filterModel: Account
        val searchResult: MutableList<Account> = mutableListOf()
        val accountType_Val: AccountType? = SH_Formatter.toValidEnum(accountType,AccountType::class.java)
        if (!SH_Formatter.isValidCondition(accountType_Val) || accountType_Val == AccountType.PERSONAL) {
            filterModel = getAccountModel(AccountType.PERSONAL,email,nickname)
            accountUseCase.setSpecificFields(
                account = filterModel,
                firstName = firstName,
                lastName = lastName,
                birthDate = SH_Formatter.toValidDate(birthDate),
                gender = gender
            )
            searchResult.addAll(accountUseCase.search(filterModel))
        }
        if (!SH_Formatter.isValidCondition(accountType_Val) || accountType_Val == AccountType.BRAND) {
            filterModel = getAccountModel(AccountType.BRAND,email,nickname)
            accountUseCase.setSpecificFields(
                account = filterModel,
                businessName = businessName,
                country = country,
                taxNumber = taxNumber
            )
            searchResult.addAll(accountUseCase.search(filterModel))
        }
        if (!SH_Formatter.isValidCondition(accountType_Val) || accountType_Val == AccountType.ADMIN) {
            filterModel = getAccountModel(AccountType.ADMIN,email,nickname)
            accountUseCase.setSpecificFields(
                account = filterModel,
                phoneNumber = phoneNumber
            )
            searchResult.addAll(accountUseCase.search(filterModel))
        }
        return searchResult
    }

    suspend fun post(posType: String = "",title: String = "", description: String? = null, bannerUrl: String? = null,requiredUnits: Int = -1, paidUnits: Int = -1,discountOnPaidUnits: Float = -1f,startInstant: LocalDateTime? = null, endInstant: LocalDateTime? = null): Boolean {
        var newPost: Post
        val posType_Val = SH_Formatter.toValidEnum(posType, PostType::class.java)
        when(posType_Val) {
            PostType.PUBLICATION -> {
                newPost = PublicationPost(
                    publishedInstant = LocalDateTime.now(),
                    status = PostStatus.PUBLISHED,
                    title = title,
                    description = description,
                    discount = 0f,
                    authorType = getClientType(),
                    authorId = client!!.id!!
                )
            }
            PostType.PROMOTION -> {
                if(getClientType() != AccountType.BRAND) return false
                newPost = PromotionPost(
                    publishedInstant = LocalDateTime.now(),
                    status = PostStatus.PUBLISHED,
                    bannerUrl = bannerUrl,
                    requiredUnits = requiredUnits,
                    paidUnits = paidUnits,
                    discountOnPaidUnits = discountOnPaidUnits,
                    startInstant = startInstant?: LocalDateTime.now(),
                    endInstant = endInstant,
                    authorId = client!!.id!!
                )

            }
            else -> return false
        }
        return try {
            postUseCase.post(newPost)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getPosts(pageNumber: Int = 0): List<Post> {
        return postUseCase.search(null as Post?)
    }

    suspend fun getPublicationById(id: Int): PublicationPost? {
        return postUseCase.getPublicationById(id)
    }

    suspend fun getVariantsByPublicationId(id: Int): MutableList<Variant> {
        return postUseCase.search(Variant(publicationId = id))
    }

    suspend fun getMediasByVariantId(id: Int): MutableList<VariantMedia> {
        return postUseCase.search(VariantMedia(variantId = id))
    }

    suspend fun getColorsByVariants(variants: List<Variant>): MutableList<VariantColor> {
        val searchResult: MutableList<VariantColor> = mutableListOf()
        variants.forEach {
            if(it.colorId != null) {
                var variantColor = postUseCase.getVariantColorById(id = it.colorId!!)
                if(variantColor != null) searchResult.add(variantColor)
            }
        }
        return searchResult
    }

    suspend fun getSizesByVariants(variants: List<Variant>): MutableList<VariantSize> {
        val searchResult: MutableList<VariantSize> = mutableListOf()
        variants.forEach {
            if(it.sizeId != null) {
                var variantSize = postUseCase.getVariantSizeById(id = it.sizeId!!)
                if(variantSize != null) searchResult.add(variantSize)
            }
        }
        return searchResult
    }

    //Carrito

    private val _carrito = MutableStateFlow<List<BagItem>>(emptyList())
    val carrito: StateFlow<List<BagItem>> = _carrito

    fun agregarAlCarrito(item: BagItem) {
        val listaActual = _carrito.value.toMutableList()
        val index = listaActual.indexOfFirst {
            it.publicationPost.id == item.publicationPost.id &&
                    it.variant.id == item.variant.id

        }

        if (index != -1) {
            val existente = listaActual[index]
            val actualizado = existente.copy(cantidad = existente.cantidad + item.cantidad)
            listaActual[index] = actualizado
        } else {
            listaActual.add(item)
        }

        _carrito.value = listaActual
        mostrarMensajeTemporal("Producto agregado al carrito")
    }


    fun eliminarDelCarrito(item: BagItem) {
        _carrito.value = _carrito.value - item
    }

    fun vaciarCarrito() {
        _carrito.value = emptyList()
    }


    private val _mensajeCarrito = MutableStateFlow<String?>(null)
    val mensajeCarrito: StateFlow<String?> = _mensajeCarrito

    fun mostrarMensajeTemporal(mensaje: String) {
        _mensajeCarrito.value = mensaje
        viewModelScope.launch {
            delay(2000)  // Mostrar por 2 segundos
            _mensajeCarrito.value = null
        }
    }


    fun getClientType(): AccountType {
        return when(this.client) {
            is AdminAccount -> AccountType.ADMIN
            is BrandAccount -> AccountType.BRAND
            else -> AccountType.PERSONAL
        }
    }

    fun getClient(): Account? {
        return this.client
    }

    private fun getAccountModel(accountType: AccountType, email: String = "", nickname: String = "", password: String = ""): Account {
        return when(accountType) {
            AccountType.PERSONAL -> PersonalAccount(email = email, nickname = nickname, password = password)
            AccountType.BRAND -> BrandAccount(email = email, nickname = nickname, password = password)
            AccountType.ADMIN -> AdminAccount(email = email, nickname = nickname, password = password)
        }
    }
}