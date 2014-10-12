EXEC = main
SOURCES = $(wildcard *.cpp)
HEADERS = $(wildcard *.h*)
OBJECTS = $(SOURCES:.cpp=.o)

all: $(EXEC)

main: $(OBJECTS)
    g++ -L/Users/arunk/Code/Boost/boost_1_56_0/stage/lib -lboost_regex-mt -lboost_filesystem-mt -lboost_thread-mt     $(OBJECTS) -o $(EXEC)

%.o: %.cpp $(HEADERS)
    g++ -I/Users/arunk/Code/Boost/boost_1_56_0 -c $< -o $@

clean:
    rm -f $(EXEC) $(OBJECTS)
