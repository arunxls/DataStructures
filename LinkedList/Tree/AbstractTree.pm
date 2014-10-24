package DataStructures::Tree::AbstractTree;
{
    use strict;
    use warnings;

    use namespace::autoclean;

    use Moose;
    use MooseX::StrictConstructor;
    use Params::Validate qw(:all);

=head1 ATTRIBUTES

=head2 C<values>

Optionally specify the initial values you want to store in the LinkedList.
Takes a arrayRef of the values as input. 

=cut

    has 'values' => (
        is      => 'ro',
        isa     => 'ArrayRef[Str]',
        default => sub { [] },
    );

    no Moose;

    __PACKAGE__->meta->make_immutable;

    sub foo{
        return 1;
    }

}

1;
