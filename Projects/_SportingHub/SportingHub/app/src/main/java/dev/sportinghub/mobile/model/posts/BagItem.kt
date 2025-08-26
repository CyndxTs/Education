package dev.sportinghub.mobile.model.posts

data class BagItem(
    val variant : Variant,
    val cantidad : Int,
    val publicationPost: PublicationPost,
    val size: VariantSize?,
    val color: VariantColor?,
    val media: List<VariantMedia>
)


