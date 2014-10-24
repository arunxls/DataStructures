package DataStructures::Tree::Binary;
{
    use strict;
    use warnings FATAL => qw(all);

    use namespace::autoclean;

    use Moose;
    use MooseX::StrictConstructor;
    use Params::Validate qw(:all);
    use List::MoreUtils qw(none);

    extends 'DataStructures::Tree::AbstractTree';

    use DataStructures::Tree::Node;

    with 'MooseX::Log::Log4perl';
    with 'DataStructures::Tree::Role';

    no Moose;

    __PACKAGE__->meta->make_immutable;

    sub foo{
        return 1;
    }

}
