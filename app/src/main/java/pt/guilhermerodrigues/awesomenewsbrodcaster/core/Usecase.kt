package pt.guilhermerodrigues.awesomenewsbrodcaster.core

abstract class UseCase<in Params, out Return> {
    abstract suspend operator fun invoke(params: Params): Return
}
