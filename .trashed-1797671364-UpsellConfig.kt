package com.example.data

import android.net.Uri

data class KegelExerciseItem(
    val number: Int,
    val code: String,
    val title: String,
    val durationLabel: String = "45 segundos",
    val demonstrationPlaceholder: String = "Demonstração em vídeo passo a passo incluída na videoaula digital.",
    val explanationPlaceholder: String = "Orientação prática de postura, ativação e execução consciente da musculatura do assoalho pélvico apresentada na aula.",
)

data class FaqItem(
    val id: String,
    val question: String,
    val answer: String,
)

data class TestimonialItem(
    val id: String,
    val authorizedName: String,
    val testimony: String,
    val dateOrContext: String? = null,
    val photoDrawableRes: Int? = null,
)

data class AuthorityProfile(
    val heading: String,
    val title: String,
    val verifiableSummary: String,
    val methodologyPillars: List<String>,
    val credentialStatusNote: String,
)

/**
 * Configuração centralizada e facilmente editável da página de Upsell.
 * Todos os textos principais, preços, links de checkout, autoridade e depoimentos
 * reais podem ser ajustados aqui sem alterar os componentes de UI.
 */
object UpsellConfig {

    // --- 7. CONFIGURAÇÃO DE CHECKOUT ---
    const val UPSELL_CHECKOUT_URL: String = "https://pay.kursinha.com/c/6af2e8ff64e1a9b63f8859fb"
    const val DECLINE_URL: String = "[Fora da página]"
    const val DELIVERY_VIDEOAULA_URL: String = "" // Configurar após confirmação de pagamento

    // --- 8. CONFIGURAÇÃO DE RASTREAMENTO ---
    const val META_PIXEL_ID: String = "" // Inserir Pixel ID quando configurado
    const val GA4_MEASUREMENT_ID: String = "" // Inserir G-XXXXXXXXXX quando configurado

    // --- DADOS DA OFERTA ---
    const val MAIN_PRODUCT_SHORT = "O PROTOCOLO DA MENTE MASCULINA"
    const val MAIN_PRODUCT_FULL =
        "O Protocolo da Mente Masculina: Assumindo o Controlo Total da Sua Vida Sexual em 21 Dias."

    const val UPSELL_TITLE =
        "MÉTODO DOS 15 MINUTOS — 17 EXERCÍCIOS DE KEGEL PARA MANTER MÁXIMA RESISTÊNCIA DURANTE O SEXO."
    const val UPSELL_SHORT_TITLE = "PROTOCOLO DOS 15 MINUTOS"

    const val PROMO_PRICE = "3.000 Kz"
    const val PROMO_PRICE_COMPACT = "3.000kz"
    const val ORIGINAL_PRICE = "4.500 Kz"
    const val ORIGINAL_PRICE_COMPACT = "4.500 kz"
    const val DAILY_TIME = "Aproximadamente 15 minutos por dia"
    const val EXERCISE_DURATION = "45 segundos por exercício"
    const val DELIVERY_FORMAT = "Videoaula digital, acessível após a confirmação da compra."

    // --- SECÇÃO 1: COMPRA CONCLUÍDA ---
    const val HEADER_BADGE = "OFERTA COMPLEMENTAR EXCLUSIVA"
    const val HEADER_CONGRATS = "PARABÉNS! A SUA COMPRA FOI CONCLUÍDA!"
    const val HEADER_SUBTITLE =
        "Antes de aceder ao seu conteúdo, existe uma oportunidade complementar que poderá acrescentar uma nova dimensão à sua jornada."
    const val HEADER_DISCLAIMER =
        "Leia esta apresentação até ao fim. Esta oferta é opcional e não altera a sua compra anterior."

    // --- SECÇÃO 2: HERO ---
    const val HERO_HEADLINE =
        "VOCÊ JÁ DEU O PRIMEIRO PASSO PARA TRANSFORMAR A SUA VIDA SEXUAL. AGORA, DESCUBRA COMO TREINAR A MUSCULATURA QUE TAMBÉM PARTICIPA NO SEU DESEMPENHO E RESISTÊNCIA DURANTE O SEXO."
    const val HERO_SUBHEADLINE =
        "Conheça o Método dos 15 Minutos: uma videoaula prática com 17 exercícios de Kegel para ajudar você a desenvolver resistência, força, consciência e controlo da musculatura do assoalho pélvico, com uma rotina que pode ocupar aproximadamente 15 minutos do seu dia."
    const val HERO_HIGHLIGHT =
        "17 exercícios. Uma rotina prática. Aproximadamente 15 minutos por dia."
    const val URGENCY_CONTEXT_COPY =
        "Se estás nesta página porque acabaste de adquirir \"O PROTOCOLO DA MENTE MASCULINA\" o seu produto principal. Se deseja acrescentar esta videoaula à sua compra, pode fazê-lo agora por 3.000 Kz."

    // --- SECÇÃO 3: IDENTIFICAÇÃO COM O DESEJO ---
    const val DESIRE_TITLE =
        "PORQUE NÃO COMPLEMENTAR O SEU PROCESSO COM UM TREINO PRÁTICO?"
    const val DESIRE_BODY =
        "Conhecimento é importante. Mas aprender a aplicar esse conhecimento no dia a dia também faz diferença.\n\n" +
            "Se já decidiu investir na sua evolução pessoal e sexual adquirido O PROTOCOLO DA MENTE MASCULINA, talvez seja interessante conhecer uma forma adicional de trabalhar a consciência e o controlo muscular para ter maior resistência e força durante o sexo.\n\n" +
            "O assoalho pélvico possui músculos que participam ativamente na atividade sexual masculina. Aprender a prática de exercitá-los adequadamente pode ser uma ARMA SECRETA para o homem que já busca mudanças reais nas suas atividades sexuais."

    val desireBlocks: List<Pair<String, String>> = listOf(
        "Consciência muscular" to
            "Aprender a identificar e ativar a musculatura do assoalho pélvico para maior resistência e força durante o sexo.",
        "Prática orientada" to
            "Acompanhar 17 exercícios demonstrados em vídeo.",
        "Consistência" to
            "Desenvolver uma rotina simples que pode ser incorporada ao dia a dia.",
    )

    val mechanismSteps: List<Triple<String, String, String>> = listOf(
        Triple("01", "Aprender", "Compreender a localização e a função da musculatura do assoalho pélvico."),
        Triple("02", "Executar", "Acompanhar a demonstração dos 17 exercícios e aprender a realizá-los corretamente."),
        Triple("03", "Praticar", "Reservar aproximadamente 15 minutos do dia para desenvolver consistência na rotina."),
    )

    // --- SECÇÃO 4: APRESENTAÇÃO DA SOLUÇÃO ---
    const val SOLUTION_TITLE = "APRESENTAMOS O MÉTODO DOS 15 MINUTOS"
    const val SOLUTION_SUBTITLE =
        "Uma videoaula prática para aprender 17 exercícios de Kegel para começar a construir uma rotina de treino de 15 minutos por dia para treinar assoalho pélvico para ter mais resistência e força durante o sexo."

    val solutionFeatures: List<String> = listOf(
        "Demonstração dos exercícios em vídeo.",
        "Orientações para compreender a musculatura envolvida.",
        "Aprendizagem da execução adequada.",
        "Possibilidade de praticar em casa.",
        "Rotina diária aproximada de 15 minutos.",
    )

    // --- SECÇÃO 5: OS 17 EXERCÍCIOS (EXATOS DO PROMPT) ---
    const val EXERCISES_TITLE = "17 EXERCÍCIOS PARA INCLUIR NA SUA ROTINA"
    val exercises: List<KegelExerciseItem> = listOf(
        KegelExerciseItem(1, "01", "Exercício de Kegel 01 - Mobilidade de quadril de cachorro-pássaro"),
        KegelExerciseItem(2, "02", "Exercício de Kegel 02 - Impulso de quadril de caranguejo"),
        KegelExerciseItem(3, "03", "Exercício de Kegel 03 - Pontes de glúteos"),
        KegelExerciseItem(4, "04", "Exercício de Kegel 04 - Abduções em ponte de glúteos"),
        KegelExerciseItem(5, "05", "Exercício de Kegel 05 - Ioga borboleta"),
        KegelExerciseItem(6, "06", "Exercício de Kegel 06 - Chutes em tesoura"),
        KegelExerciseItem(7, "07", "Exercício de Kegel 07 - Elevação do quadril Frogger"),
        KegelExerciseItem(8, "08", "Exercício de Kegel 08 - Pulso de agachamento sapo"),
        KegelExerciseItem(9, "09", "Exercício de Kegel 09 - Estocada do corredor"),
        KegelExerciseItem(10, "10", "Exercício de Kegel 10 - Impulso do quadril ajoelhado"),
        KegelExerciseItem(11, "11", "Exercício de Kegel 11 - Lúcio de prancha"),
        KegelExerciseItem(12, "12", "Exercício de Kegel 12 - Prancha Jack"),
        KegelExerciseItem(13, "13", "Exercício de Kegel 13 - Mergulhos de quadril na prancha"),
        KegelExerciseItem(14, "14", "Exercício de Kegel 14 - Ponte de glúteo de sapo"),
        KegelExerciseItem(15, "15", "Exercício de Kegel 15 - Abduções de pernas sentadas"),
        KegelExerciseItem(16, "16", "Exercício de Kegel 16 - Marcha dos glúteos"),
        KegelExerciseItem(17, "17", "Exercício de Kegel 17 - Abdução da ponte de glúteos"),
    )

    // --- SECÇÃO 6: SIMPLICIDADE DA ROTINA ---
    const val ROUTINE_TITLE = "VOCÊ NÃO PRECISA PASSAR HORAS A TREINAR."
    const val ROUTINE_COPY =
        "Uma das vantagens desta proposta é a simplicidade da rotina.\n\n" +
            "Pode reservar aproximadamente 15 minutos do seu dia para acompanhar e praticar os exercícios apresentados na videoaula.\n\n" +
            "Não precisa transformar completamente a sua agenda para começar a desenvolver consistência para praticar esses exercícios."

    // --- SECÇÃO 7: TRANSFORMAÇÃO E BENEFÍCIOS ---
    const val BENEFITS_TITLE =
        "O QUE ESTA PRÁTICA PODE ACRESCENTAR À SUA JORNADA DE TRANSFORMAÇÃO MASCULINA?"
    val benefitsList: List<String> = listOf(
        "Desenvolver maior consciência da musculatura do assoalho pélvico.",
        "Aprender a contrair e relaxar essa musculatura de forma adequada durante o sexo.",
        "Incorporar exercícios específicos numa rotina de autocuidado para uma vida mais saudável.",
        "Compreender melhor o papel do assoalho pélvico na função masculina.",
        "Aprender uma prática complementar relacionada com o controlo muscular para ter maior resistência e força na hora do sexo.",
        "Ter acesso a demonstrações visuais em vez de depender apenas de explicações escritas.",
    )
    const val BENEFITS_CLOSING =
        "Pois os exercícios garantem mais prolongamento da relação sexual, e contribuem no processo da melhoria da ereção."

    // --- SECÇÃO 8: DIFERENCIAÇÃO ---
    const val DIFFERENTIATION_TITLE =
        "ISTO NÃO É APENAS MAIS INFORMAÇÃO. É UMA DEMONSTRAÇÃO PRÁTICA."
    const val DIFFERENTIATION_COPY =
        "Em vez de ficar apenas com conceitos teóricos, terá uma videoaula orientada para aprender e acompanhar 17 exercícios específicos de Kegel.\n\n" +
            "O objetivo é facilitar a compreensão da execução e permitir que desenvolva uma rotina própria de prática de 15 minutos adaptado ao seu dia á dia."
    val knowledgeOnlyPoints: List<String> = listOf(
        "Ler sobre exercícios.",
        "Tentar descobrir sozinho como executar.",
        "Não ter uma demonstração visual organizada.",
    )
    val videoLessonPoints: List<String> = listOf(
        "Acompanhar demonstrações.",
        "Conhecer os 17 exercícios apresentados.",
        "Aprender a organizar uma rotina prática que se encaixa no teu dia á dia.",
    )

    // --- SECÇÃO 9 & 10: EMPILHAMENTO, ANCORAGEM, ESCASSEZ E EXCLUSIVIDADE ---
    const val VALUE_STACK_TITLE = "TUDO O QUE RECEBE AO ADICIONAR ESTA EXPERIÊNCIA"
    const val ANCHOR_TITLE =
        "UMA PEQUENA ADIÇÃO COMPLEMENTAR À SUA JORNADA DE RECONSTRUÇÃO MASCULINA, COM UM INVESTIMENTO ACESSÍVEL."
    const val ANCHOR_COPY =
        "Depois de investir na sua evolução através de O Protocolo da Mente Masculina, pode complementar a sua experiência com uma videoaula prática dedicada ao treino da musculatura do assoalho pélvico para ter maior resistência e força durante as tuas atividades sexuais.\n\n" +
            "Por apenas 3.000 Kz, terá acesso à vídeoaula com os 17 exercícios de Kegel sendo executado na prática."

    const val SCARCITY_COPY =
        "Está oferta está disponível á esse preço só para quem já adquiriu o produto principal [O PROTOCOLO DA MENTE MASCULINA], mas só pelas próximas 24 horas após o término desse período o valor da vídeoaula volta para 4.500 kz que o preço original."
    const val EXCLUSIVITY_COPY =
        "A videoaula é um complemento exclusivo e específico disponibilizado neste momento da jornada de compra á esse valor porque já adquiriste o produto principal. Se saíres sem adquirir nunca terás acesso novamente á oferta desse conteúdo á esse preço mas sim ao valor original de 4.500kz"

    // --- AUTORIDADE (DADOS VERIFICÁVEIS EDITÁVEIS) ---
    val authorityProfile = AuthorityProfile(
        heading = "METODOLOGIA ESTRUTURADA COM DEMONSTRAÇÃO PRÁTICA",
        title = "Responsável pelo Conteúdo & Estrutura Pedagógica",
        verifiableSummary =
            "A credibilidade do Método dos 15 Minutos assenta na demonstração visual organizada dos 17 exercícios específicos para o assoalho pélvico masculino, com duração objetiva de 45 segundos por exercício para aplicação imediata em casa.",
        methodologyPillars = listOf(
            "Sequência progressiva de 17 exercícios específicos demonstrados na íntegra em vídeo.",
            "Foco direto na consciência, contração e relaxamento da musculatura do assoalho pélvico.",
            "Estrutura pensada para quem procura clareza prática sem promessas irreais ou complexidade excessiva.",
        ),
        credentialStatusNote =
            "Espaço reservado na configuração editorial (UpsellConfig.authorityProfile) para inserir apenas qualificações e registos verificáveis do instrutor responsável.",
    )

    // --- SECÇÃO 11: PROVA SOCIAL (SEM DEPOIMENTOS FICTÍCIOS) ---
    const val TESTIMONIALS_TITLE = "EXPERIÊNCIAS REAIS DE QUEM JÁ CONHECEU O MÉTODO."
    // Mantenha vazio até que depoimentos autênticos e autorizados sejam fornecidos.
    val testimonials: List<TestimonialItem> = emptyList()

    // --- SECÇÃO 12: GARANTIA ---
    const val GUARANTEE_TITLE = "QUEREMOS QUE A SUA DECISÃO SEJA SEGURA."
    const val GUARANTEE_COPY =
        "Confiamos tanto nos exercícios da nossa vídeoaula que oferecemos 15 dias de garantia incondicional. Assista às aulas, aplique os passos dos exercícios e, se em até 15 dias você decidir que não gostou da didática ou do conteúdo da aula, devolvemos cada centavo do seu dinheiro."

    // --- SECÇÃO 13: OBJEÇÕES E RESPOSTAS (FAQ) ---
    const val FAQ_TITLE = "PERGUNTAS FREQUENTES"
    val faqItems: List<FaqItem> = listOf(
        FaqItem(
            id = "faq_equipment",
            question = "Preciso de equipamentos para praticar?",
            answer = "Não, a proposta da videoaula é apresentar exercícios que podem ser praticados sem depender de equipamentos específicos, conforme as demonstrações e instruções fornecidas.",
        ),
        FaqItem(
            id = "faq_time",
            question = "Quanto tempo preciso reservar por dia?",
            answer = "A rotina foi concebida para ocupar aproximadamente 15 minutos por dia.",
        ),
        FaqItem(
            id = "faq_experience",
            question = "Preciso ter experiência anterior?",
            answer = "A videoaula foi pensada para ajudar o utilizador a compreender e acompanhar os exercícios apresentados mesmo sem terem experiência anterior.",
        ),
        FaqItem(
            id = "faq_results",
            question = "Os exercícios garantem que vou resolver problemas de desempenho e resistência sexuais?",
            answer = "Sim. Os exercícios de Kegel foram criados especialmente para trabalhar a musculatura do assoalho pélvico masculino que são responsáveis por desempenhar o papel da resolução e condição de resistência e força nas atividades sexuais do homem.",
        ),
        FaqItem(
            id = "faq_delivery",
            question = "Como recebo a videoaula?",
            answer = "Após a confirmação do pagamento, o acesso deverá ser disponibilizado através do mecanismo de entrega configurado que é um link.",
        ),
        FaqItem(
            id = "faq_mandatory",
            question = "É obrigatório comprar esta oferta?",
            answer = "Não. Trata-se de uma oferta complementar e opcional mas que é de extrema importância para o processo de cura da sua resposta sexual. A sua compra principal continua válida independentemente desta decisão.",
        ),
    )

    // --- SECÇÃO 14: CTA FINAL ---
    const val FINAL_CTA_TITLE =
        "VOCÊ JÁ COMEÇOU A SUA JORNADA. QUER ADICIONAR UM TREINO PRÁTICO A ELA?"
    const val FINAL_CTA_COPY =
        "Conheça os 17 exercícios de Kegel, acompanhe as demonstrações em vídeo e descubra como incorporar aproximadamente 15 minutos de prática na sua rotina diária."
    const val PRIMARY_CTA_LABEL =
        "SIM! QUERO ADICIONAR A VIDEOAULA PROMOCIONAL EXTRA PARA COMPLETAR DEFINITIVAMENTE MEU DESENVOLVIMENTO COMO HOMEM POR APENAS 3.000kz"
    const val CTA_MICROCOPY =
        "Oferta complementar e opcional. A sua compra anterior não será afetada caso decida não adicionar esta oferta."
    const val SECONDARY_CTA_LABEL =
        "NÃO, OBRIGADO. REMOVA A VIDEOAULA EXTRA DO MEU PEDIDO, EU NÃO QUERO COMPLETAR DEFINITIVAMENTE MEU DESENVOLVIMENTO COMO HOMEM POR APENAS 3.000kz"

    fun buildCheckoutUrlWithUtms(utms: UtmParameters): String {
        val baseUri = Uri.parse(UPSELL_CHECKOUT_URL)
        val builder = baseUri.buildUpon()
        if (utms.utmSource.isNotBlank()) builder.appendQueryParameter("utm_source", utms.utmSource)
        if (utms.utmMedium.isNotBlank()) builder.appendQueryParameter("utm_medium", utms.utmMedium)
        if (utms.utmCampaign.isNotBlank()) builder.appendQueryParameter("utm_campaign", utms.utmCampaign)
        if (utms.utmContent.isNotBlank()) builder.appendQueryParameter("utm_content", utms.utmContent)
        if (utms.utmTerm.isNotBlank()) builder.appendQueryParameter("utm_term", utms.utmTerm)
        return builder.build().toString()
    }
}
