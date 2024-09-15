package com.example.myapplication

class Trail( private val name:String, private val info:String, private val imageResourceId:Int, private val level:String){
    companion object {
        val trails = arrayOf(
            Trail("Góry Stołowe", "Góry Stołowe, leżące w południowo-zachodniej Polsce, to niezwykle malownicze pasmo górskie, które wyróżnia się wyjątkową formą ukształtowania terenu. Cechą charakterystyczną tych gór są ich stołowe szczyty, które mają płaskie wierzchołki, co sprawia, że wyglądają jakby zostały równo odcięte.\n" +
                    "\n" +
                    "Te góry to prawdziwy raj dla miłośników przyrody i turystyki, oferujący mnóstwo szlaków o różnym poziomie trudności. Łatwe i przyjemne trasy prowadzą przez urokliwe lasy, malownicze doliny oraz wzdłuż ciekawych formacji skalnych. Na trasach można podziwiać różnorodne formacje skalne, w tym fenomenalne skalne labirynty, które zachwycają swoją niepowtarzalną strukturą.\n" +
                    "\n" +
                    "Góry Stołowe są częścią Parku Narodowego Gór Stołowych, który chroni unikalne środowisko naturalne i bogactwo przyrodnicze regionu. Oprócz wędrówek, warto zwrócić uwagę na liczne punkty widokowe, z których rozciągają się przepiękne panoramy. Nieopodal znajdują się także malownicze wioski i miasteczka, które oferują możliwość poznania lokalnej kultury i tradycji.\n" +
                    "\n" +
                    "Dzięki swojemu wyjątkowemu krajobrazowi i łatwym szlakom, Góry Stołowe są idealnym miejscem na rodzinne wycieczki oraz dla osób szukających relaksu w otoczeniu natury.", R.drawable.gory_stolowe, "easy"),
            Trail("Góry Świętokrzyskie", "Trail B is...\n\n\n\n\n\n\n\n\n\n", R.drawable.gory_swietokrzyskie, "easy"),
            Trail("Małe Pieniny", "Trail C is...\n\n\n\n\n\n\n\n\n\n\n", R.drawable.male_pieniny, "easy"),
            Trail("Pieniny", "Trail B is...\n\n\n\n\n\n\n\n\n\n\n\n", R.drawable.pieniny, "easy"),
            Trail("Bieszczady", "Trail A is...\n\n\n\n\n\n\n\n\n\n\n\n", R.drawable.bieszczady, "hard"),
            Trail("Karkonosze", "Trail A is...\n\n\n\n\n\n\n\n\n\n", R.drawable.karkonosze, "hard"),
            Trail("Tatry Wysokie", "Trail A is...\n\n\n\n\n\n\n\n\n\n\n", R.drawable.tatry_wysokie, "hard"),
            Trail("Tatry Zachodnie", "Trail A is...\n\n\n\n\n\n\n\n\n\n\n\n", R.drawable.tatry_zachodnie, "hard"),
        )
    }
    fun getImage(): Int {
        return imageResourceId
    }
    fun getName(): String {
        return name
    }

    fun getInfo(): String {
        return info
    }

    fun getLevel(): String {
        return level
    }

}