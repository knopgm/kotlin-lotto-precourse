package lotto

val outputView = OutputView()
val inputView = InputView()

fun callIntroAndBudget(): Int {
    outputView.printWelcomeMessage()
    val budget = inputView.readBudget()

    return budget
}

fun callTicketLogic(budget: Int):List<Lotto> {
    val numberOfTickets = budget / 1000
    val ticketCreator = TicketGenerator()
    val ticketsList = ticketCreator.generateMultiples(numberOfTickets)
    outputView.printPurchasedTickets(numberOfTickets, ticketsList)


    return ticketsList
}

fun callWinningTicketLogic(ticketsList: List<Lotto>): List<TicketResults> {
    outputView.printWinningNumbersPrompt()
    val readWinningNumbers = inputView.readWinningNumbers()
    outputView.printBonusNumberPrompt()
    val readBonusNumber = inputView.readBonusNumber()
    outputView.printWinningStatisticsTitle()
    val ticketChecker = TicketChecker()
    val winningResults = ticketChecker.calculateTicketsResults(ticketsList, readWinningNumbers, readBonusNumber)
    outputView.printResults(winningResults)

    return winningResults
}

fun callProfitRateLogic(budget: Int, winningResults:List<TicketResults>) {
    val profitCalculator = ProfitCalculator()
    val profitRate = profitCalculator.calculate(budget, winningResults)
    outputView.printProfitRate(profitRate)
}

fun main() {
    // First input read and checked
    val budget = callIntroAndBudget()
    // Second Tickets created, checked and printed
    val ticketsList = callTicketLogic(budget)
    // Third input read, checked and print winners
    val winningResults = callWinningTicketLogic(ticketsList)
    // Fourth check and print profit rate
    callProfitRateLogic(budget, winningResults)
}

