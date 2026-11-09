import os
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

# Load data.
df = pd.read_csv("output.csv")
os.makedirs("graphs_output", exist_ok=True)
sns.set_theme(style="whitegrid")

data_types = df["DataType"].unique()
orders = df["Order"].unique()

for dt in data_types:
  for ord_val in orders:
    sub_df = df[(df["DataType"] == dt) & (df["Order"] == ord_val)].copy()
    sub_df["Dimension"] = sub_df["Dimension"].astype(str)

    # BOXPLOT
    g_box = sns.catplot(
        data=sub_df,
        x="Algorithm",
        y="TimeMs",
        hue="Algorithm",
        col="Dimension",
        kind="box",
        palette="Set2",
        height=4,
        aspect=1.1,
        sharey=False, # Independent y axes scale.
    )
    g_box.figure.subplots_adjust(top=0.85)
    g_box.figure.suptitle(
        f"Boxplot - {dt} ({ord_val})", fontsize=14, fontweight="bold"
    )
    g_box.set_axis_labels("Algorithm", "Time (ms)")
    plt.savefig(f"graphs_output/boxplot_{dt}_{ord_val}.png", dpi=300)
    plt.close()

print(
    "All graphs have been generated and saved in the 'graphs_output' directory."
)

# Min, First Quartile, Median, Third Quartile, Max
five_num = (
    df.groupby(["Dimension", "Algorithm"])["TimeMs"]
    .quantile([0.0, 0.25, 0.5, 0.75, 1.0])
    .unstack(level=-1)
)
five_num.columns = ["Min", "Q1", "Median", "Q3", "Max"]

# Add a new column with the full name: "Algorithm (DataType, Order, Dimension)"
five_num["FullName"] = (
    five_num.index.get_level_values("Algorithm").astype(str)
    + " ("
    + five_num.index.get_level_values("Dimension").astype(str)
    + ")"
)

five_num.to_csv("graphs_output/descriptive_statistics.csv", index=False)