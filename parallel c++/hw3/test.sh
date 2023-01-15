indent() { sed 's/^/    /'; }
omit_first_line() { tail -n +2; }
for input_filename in input*.txt; do
    input_file=$(cat ${input_filename})
    echo $input_file | srun -N 5 ./hw3/homework --rtol=0.000001 --check-steps=50 >parallel_output.txt &
    pid1=$!
    echo $input_file | srun -N 1 ./hw3/homework --rtol=0.000001 --check-steps=50 >output.txt &
    pid2=$!

    wait $pid1
    wait $pid2

    echo ${input_filename} {
    diff parallel_output.txt output.txt | omit_first_line | indent
    echo -e '}\n'
done
rm ~/parallel_output.txt ~/output.txt
