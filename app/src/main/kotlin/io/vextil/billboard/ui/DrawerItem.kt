package io.vextil.billboard.ui

class DrawerItem(title: Int, color: Int, icon: Icon) {

    public val title = title
    public val color = color
    public val icon = icon
    private var fragment: Any? = null
    private val children = arrayListOf<DrawerItemChild>()

    fun fragment(fragment: Any): DrawerItem {
        this.fragment = fragment
        return this
    }

    fun hasChild(): Boolean {
        return children.size > 0
    }

    fun addChild(title: Int, icon: Icon): DrawerItemChild {
        val child = DrawerItemChild(title, icon, this)
        children.add(child)
        return child
    }

    fun getChild(id: Int): DrawerItemChild {
        return children[id]
    }

    class DrawerItemChild(title: Int, icon: Icon, parent: DrawerItem) {

        public val title = title
        public val icon = icon
        private val parent = parent
        public var fragment: Any? = null

        fun fragment(fragment: Any): DrawerItem {
            this.fragment = fragment
            return parent
        }
    }

}