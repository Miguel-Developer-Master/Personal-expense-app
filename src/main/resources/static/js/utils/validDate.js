export function isValidDate(date) {
    if (!/^\d{4}-\d{2}-\d{2}$/.test(date)) {
        return false;
    }
    const parsedDate = new Date(`${date}T00:00:00.000Z`);

    return (Number.isFinite(parsedDate.getTime()) && parsedDate.toISOString().startsWith(date));
}