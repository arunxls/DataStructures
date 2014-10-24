package DataStructures::LinkedList;
{
    use strict;
    use warnings FATAL => qw(all);

    use namespace::autoclean;

    use MooseX::StrictConstructor;
    use MooseX::AbstractFactory;

    use DataStructures::LinkedList::Single;

    my %implementationClass = (
        'single' => 'DataStructures::LinkedList::Single',
        'double' => 'DataStructures::LinkedList::Double',
    );

    implementation_class_via sub {
        my $type = shift;
        die "'$type' is not implemented!"
            if ( !defined $implementationClass{$type} );
        $implementationClass{$type};
    };
}

1;